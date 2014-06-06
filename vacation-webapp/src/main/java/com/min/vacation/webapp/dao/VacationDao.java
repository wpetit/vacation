package com.min.vacation.webapp.dao;

import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.min.vacation.webapp.model.PaginatedModel;
import com.min.vacation.webapp.model.SortType;
import com.min.vacation.webapp.model.Vacation;

/**
 * The {@link VacationDao} class.
 * 
 * @author WPETIT
 * 
 */
@Component
public class VacationDao {
    /**
     * Return <pageSize> vacation from the startIndex.
     * 
     * @param startIndex
     *            the startIndex.
     * @param pageSize
     *            the pageSize.
     * @return vacations found
     */
    public PaginatedModel<Vacation> findAll(final int startIndex, final int pageSize,
            final String sortAttribute, final SortType sortType) {
        Vacation vacation = new Vacation();
        vacation.setFrom(new Date());
        vacation.setTo(new Date());
        vacation.setType("Congés payés");

        PaginatedModel<Vacation> paginatedModel = new PaginatedModel<Vacation>();
        paginatedModel.setStartIndex(startIndex);
        paginatedModel.setTotal(10);
        paginatedModel
                .setModelList(Arrays.asList(vacation, vacation, vacation, vacation, vacation));

        return paginatedModel;
    }
}
