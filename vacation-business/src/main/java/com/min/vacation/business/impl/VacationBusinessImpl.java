package com.min.vacation.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /** {@inheritDoc} **/
    @Override
    public int getVacationWorkingDaysCount(final String username, final int vacationTypeId) {
        return 0;
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
