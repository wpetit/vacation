package com.min.vacation.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.vacation.business.VacationBusiness;
import com.min.vacation.dao.VacationDao;

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

    /** {@inheritDoc} **/
    @Override
    public int getVacationWorkingDaysCount(final String username,
            final int vacationTypeId) {
        return 0;
    }
}
