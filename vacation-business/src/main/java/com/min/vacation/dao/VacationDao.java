package com.min.vacation.dao;

import java.util.List;

import com.min.vacation.model.PaginatedModel;
import com.min.vacation.model.SortType;
import com.min.vacation.model.Vacation;

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

    /**
     * Return the vacations with the given user and the given vacation type.
     * 
     * @param username
     *            the username
     * @param vacationTypeId
     *            the vacation type id
     * @return the vacations
     */
    List<Vacation> getVacationByUsernameAndType(final String username,
            final int vacationTypeId);

    /**
     * Delete all vacation that belong to the given vacation type id.
     * 
     * @param id
     *            the vacation type id
     */
    void deleteVacationByVacationType(int id);
}
