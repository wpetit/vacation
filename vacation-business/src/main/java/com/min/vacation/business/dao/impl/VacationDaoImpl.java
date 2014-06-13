package com.min.vacation.business.dao.impl;

import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.min.vacation.business.dao.VacationDao;
import com.min.vacation.business.model.PaginatedModel;
import com.min.vacation.business.model.SortType;
import com.min.vacation.business.model.Vacation;

/**
 * The {@link VacationDaoImpl} class.
 * 
 * @author WPETIT
 */
@Repository
public class VacationDaoImpl implements VacationDao {
    /** {@inheritDoc} */
    @Override
    public PaginatedModel<Vacation> findAll(final int startIndex,
            final int pageSize, final String sortAttribute,
            final SortType sortType) {
        Vacation vacation = new Vacation();
        vacation.setFrom(new Date());
        vacation.setTo(new Date());
        vacation.setType("Congés payés");

        PaginatedModel<Vacation> paginatedModel = new PaginatedModel<Vacation>();
        paginatedModel.setStartIndex(startIndex);
        paginatedModel.setTotal(10);
        paginatedModel.setModelList(Arrays.asList(vacation, vacation, vacation,
                vacation, vacation));

        return paginatedModel;
    }
}
