package com.min.vacation.business.dao;

import com.min.vacation.business.model.PaginatedModel;
import com.min.vacation.business.model.SortType;
import com.min.vacation.business.model.Vacation;

/**
 * The {@link VacationDao} interface.
 * 
 * @author WPETIT
 */
public interface VacationDao {

    /**
     * Return <pageSize> vacation from the startIndex.
     * 
     * @param username
     *            user the vacations belongs to.
     * @param startIndex
     *            the startIndex.
     * @param pageSize
     *            the pageSize.
     * @param sortAttribute
     *            the sortAttribute.
     * @param sortType
     *            the sortType.
     * @return vacations found
     */
    PaginatedModel<Vacation> findUserVacations(String username, int startIndex,
            int pageSize, String sortAttribute, SortType sortType);

    /**
     * Retrieve the number of user vacations.
     * 
     * @param username
     *            user the vacations belongs to.
     * @return the number of user vacations.
     */
    int getUserVacationsCount(String username);

    /**
     * Save the given vacation.
     * 
     * @param vacation
     *            the vacation to save
     */
    void save(Vacation vacation);
}
