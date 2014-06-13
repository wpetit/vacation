package com.min.vacation.business.dao;

import com.min.vacation.business.model.PaginatedModel;
import com.min.vacation.business.model.SortType;
import com.min.vacation.business.model.Vacation;

/**
 * The {@link VacationDao} interface.
 * 
 * @author WPETIT
 * 
 */
public interface VacationDao {

    /**
     * Return <pageSize> vacation from the startIndex.
     * 
     * @param startIndex
     *            the startIndex.
     * @param pageSize
     *            the pageSize.
     * @return vacations found
     */
    public PaginatedModel<Vacation> findAll(int startIndex, int pageSize, String sortAttribute,
            SortType sortType);

}
