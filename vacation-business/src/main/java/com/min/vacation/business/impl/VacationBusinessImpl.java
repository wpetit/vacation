package com.min.vacation.business.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.vacation.business.DayOffBusiness;
import com.min.vacation.business.VacationBusiness;
import com.min.vacation.dao.VacationDao;
import com.min.vacation.dao.VacationTypeDao;
import com.min.vacation.exception.ExceptionCode;
import com.min.vacation.exception.FunctionalException;
import com.min.vacation.model.PaginatedModel;
import com.min.vacation.model.SortType;
import com.min.vacation.model.Vacation;
import com.min.vacation.model.VacationType;

/**
 * The {@link VacationBusinessImpl} class.
 *
 * @author wpetit
 */
@Component
public class VacationBusinessImpl implements VacationBusiness {
    /** The vacationDao. */
    @Autowired
    private VacationDao vacationDao;

    /** The vacationTypeDao. */
    @Autowired
    private VacationTypeDao vacationTypeDao;

    /** The dayOffBusiness. */
    @Autowired
    private DayOffBusiness dayOffBusiness;

    /** {@inheritDoc} **/
    @Override
    public double getVacationWorkingDaysCount(final String username, final int vacationTypeId) {
        List<Vacation> vacationList = vacationDao.getVacationByUsernameAndType(username,
                vacationTypeId);
        double nbWorkingDays = 0;
        for (Vacation vacation : vacationList) {
            nbWorkingDays += getNumberOfWorkingDays(vacation);
        }
        return nbWorkingDays;
    }

    /**
     * Return the number of working days during vacation.
     *
     * @param vacation
     *            the vacation
     * @return the number of working days
     */
    private double getNumberOfWorkingDays(final Vacation vacation) {
        double nbWorkingDays = 0;
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(vacation.getFrom());
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(vacation.getTo());
        endCalendar.add(Calendar.DATE, 1);
        DateTime start = new DateTime(vacation.getFrom());
        DateTime end = new DateTime(endCalendar.getTime());
        if (endCalendar.after(startCalendar)) {
            Calendar lastDay = Calendar.getInstance();
            lastDay.setTime(vacation.getTo());
            Days daysWithDayOff = Days.daysBetween(start, end);
            nbWorkingDays += daysWithDayOff.getDays();
            nbWorkingDays -= getNumberOfDayOff(startCalendar, lastDay);
            if (start.getHourOfDay() == 12) {
                nbWorkingDays -= 0.5;
            }
            if (end.getHourOfDay() == 12) {
                nbWorkingDays -= 0.5;
            }
        }
        return nbWorkingDays;
    }

    /**
     * Return the number of day off in the given period.
     *
     * @param start
     *            the period start
     * @param end
     *            the period end
     * @return the number of day
     */
    private int getNumberOfDayOff(final Calendar start, final Calendar end) {
        int nbOfDayOff = 0;
        for (Date date = start.getTime(); !start.after(end); start.add(Calendar.DATE, 1), date = start
                .getTime()) {
            if (dayOffBusiness.isDayOff(date)) {
                nbOfDayOff++;
            }
        }
        return nbOfDayOff;
    }

    /** {@inheritDoc} */
    @Override
    public PaginatedModel<Vacation> findUserVacations(final String username, final int startIndex,
            final int pageSize, final String sortAttribute, final SortType sortType) {
        return vacationDao.findUserVacations(username, startIndex, pageSize, sortAttribute,
                sortType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final Vacation vacation) throws FunctionalException {
        // check vacation period against vacation type period
        checkVacationPeriod(vacation);
        // check vacation is not on another one
        checkVacationNotOnAnother(vacation);
        // check if the number of days of the vacation + current balance does not exceed
        // vacation type nb days
        double nbDaysOfType = getVacationWorkingDaysCount(vacation.getUser().getUsername(),
                vacation.getType().getId());
        double nbDaysCurrentVacation = getNumberOfWorkingDays(vacation);
        double nbDaysAllowed = vacation.getType().getNumberOfDays();
        double newNbVacationOfType = nbDaysOfType + nbDaysCurrentVacation;
        if (newNbVacationOfType <= nbDaysAllowed) {
            vacationDao.save(vacation);
        } else {
            throw new FunctionalException(ExceptionCode.TOO_MANY_VACATION_FOR_TYPE.getCode());
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<Vacation> getVacationByUsernameAndType(final String username,
            final int vacationTypeId) {
        return vacationDao.getVacationByUsernameAndType(username, vacationTypeId);
    }

    /** {@inheritDoc} */
    @Override
    public void save(final VacationType vacationType) {
        vacationTypeDao.save(vacationType);
    }

    /** {@inheritDoc} */
    @Override
    public List<VacationType> getUserVacationType(final String username,
            final String sortAttribute, final SortType sortType) {
        return vacationTypeDao.getUserVacationType(username, sortAttribute, sortType);
    }

    /** {@inheritDoc} */
    @Override
    public VacationType getVacationTypeById(final int id) {
        return vacationTypeDao.getVacationTypeById(id);
    }

    /** {@inheritDoc} **/
    @Override
    public void updateVacationType(final VacationType vacationType) {
        vacationTypeDao.update(vacationType);
    }

    /** {@inheritDoc} **/
    @Override
    public void deleteVacationType(final int id) {
        vacationDao.deleteVacationByVacationType(id);
        vacationTypeDao.delete(getVacationTypeById(id));
    }

    /** {@inheritDoc} **/
    @Override
    public Vacation getVacation(final int id) {
        return vacationDao.getVacationById(id);
    }

    /** {@inheritDoc} **/
    @Override
    public void updateVacation(final Vacation vacationToUpdate) throws FunctionalException {
        VacationType vacationType = vacationTypeDao.getVacationTypeById(vacationToUpdate.getType()
                .getId());
        vacationToUpdate.setType(vacationType);
        checkVacationPeriod(vacationToUpdate);

        // check vacation not on another one
        checkVacationNotOnAnother(vacationToUpdate);

        List<Vacation> vacationList = vacationDao.getVacationByUsernameAndType(vacationToUpdate
                .getUser().getUsername(), vacationToUpdate.getType().getId());
        double nbWorkingDaysInVacationType = 0;
        for (Vacation vacation : vacationList) {
            if (vacation.getId() != vacationToUpdate.getId()) {
                nbWorkingDaysInVacationType += getNumberOfWorkingDays(vacation);
            }
        }

        double nbWorkingDaysInVacationToUpdate = getNumberOfWorkingDays(vacationToUpdate);

        // TODO check vacation number before updating
        if (nbWorkingDaysInVacationType + nbWorkingDaysInVacationToUpdate > vacationType
                .getNumberOfDays()) {
            throw new FunctionalException(ExceptionCode.TOO_MANY_VACATION_FOR_TYPE.getCode());
        } else {
            vacationDao.update(vacationToUpdate);
        }

    }

    /** {@inheritDoc} **/
    @Override
    public void deleteVacation(final int id) {
        Vacation vacation = getVacation(id);
        vacationDao.delete(vacation);
    }

    /**
     * Check vacation period against vacation type period
     *
     * @param vacation
     *            the vacation to check
     * @throws FunctionalException
     *             if the period is not included in vacation type period
     */
    private void checkVacationPeriod(final Vacation vacation) throws FunctionalException {
        if (vacation.getFrom().before(vacation.getType().getBeginDate())
                || vacation.getTo().after(vacation.getType().getEndDate())) {
            throw new FunctionalException(
                    ExceptionCode.VACATION_PERIOD_NOT_IN_TYPE_PERIOD.getCode());

        }
    }

    /**
     * Check the given vacation is not on another vacation of the same type.
     * 
     * @param vacation
     *            the vacation to check
     */
    private void checkVacationNotOnAnother(final Vacation vacation) throws FunctionalException {
        boolean isOnAnother = false;
        List<Vacation> vacationsOfType = getVacationByUsernameAndType(vacation.getUser()
                .getUsername(), vacation.getType().getId());
        for (Vacation vacationsSaved : vacationsOfType) {

            if (vacationsSaved.getId() != vacation.getId()
                    && (between(vacation.getFrom(), vacationsSaved.getFrom(),
                            vacationsSaved.getTo()) || between(vacation.getTo(),
                            vacationsSaved.getFrom(), vacationsSaved.getTo()))) {
                isOnAnother = true;
                break;
            }
        }
        if (isOnAnother) {
            throw new FunctionalException(
                    ExceptionCode.VACATION_ALREADY_EXISTS_IN_THIS_PERIOD_FOR_THIS_TYPE.getCode());
        }
    }

    private boolean between(final Date date, final Date from, final Date to) {
        return date.after(from) && date.before(to);
    }

}
