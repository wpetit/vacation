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

    /** The vacationtypeDao. */
    @Autowired
    private VacationTypeDao vacationtypeDao;

    /** The dayOffBusiness. */
    @Autowired
    private DayOffBusiness dayOffBusiness;

    /** {@inheritDoc} **/
    @Override
    public int getVacationWorkingDaysCount(final String username, final int vacationTypeId) {
        List<Vacation> vacationList = vacationDao.getVacationByUsernameAndType(username,
                vacationTypeId);
        int numberOfWorkingDays = 0;
        for (Vacation vacation : vacationList) {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(vacation.getFrom());
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(vacation.getTo());
            endCalendar.add(Calendar.DATE, 1);
            Calendar lastDay = Calendar.getInstance();
            lastDay.setTime(vacation.getTo());
            DateTime start = new DateTime(vacation.getFrom());
            DateTime end = new DateTime(endCalendar.getTime());
            Days daysWithDayOff = Days.daysBetween(start, end);
            numberOfWorkingDays += daysWithDayOff.getDays();
            numberOfWorkingDays -= getNumberOfDayOff(startCalendar, lastDay);
        }
        return numberOfWorkingDays;
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

    /** {@inheritDoc} */
    @Override
    public void save(final Vacation vacation) {
        vacationDao.save(vacation);
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
        vacationtypeDao.save(vacationType);
    }

    /** {@inheritDoc} */
    @Override
    public List<VacationType> getUserVacationType(final String username) {
        return vacationtypeDao.getUserVacationType(username);
    }

    /** {@inheritDoc} */
    @Override
    public VacationType getVacationTypeById(final int id) {
        return vacationtypeDao.getVacationTypeById(id);
    }
}
