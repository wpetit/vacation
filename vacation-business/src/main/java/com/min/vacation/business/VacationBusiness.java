package com.min.vacation.business;

import java.util.List;

import com.min.vacation.model.PaginatedModel;
import com.min.vacation.model.SortType;
import com.min.vacation.model.Vacation;
import com.min.vacation.model.VacationType;

/**
 * The {@link VacationBusiness} interface.
 * 
 * @author wpetit
 */
public interface VacationBusiness {
    /**
     * Return the number of vacation days not worked.
     * 
     * @param username
     *            the username
     * @param vacationTypeId
     *            the vacation type
     * @return the number of vacation days not worked
     */
    int getVacationWorkingDaysCount(String username, int vacationTypeId);

    /**
     * Return <pageSize> vacation from the startIndex with the number of results
     * and sort specified for the given user.
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
     * Save the given {@link VacationType}.
     * 
     * @param vacationType
     *            the vacationType to save
     */
    void save(VacationType vacationType);

    /**
     * Retrieve the user vacation types.
     * 
     * @param username
     *            the user username
     * @param sortAttribute
     *            the attribute to sort on (default to "type" attribute)
     * @param sortType
     *            the sort operator (default : asc)
     * @return vacation types found
     */
    List<VacationType> getUserVacationType(String username,
            String sortAttribute, SortType sortType);

    /**
     * Retrieve a vacationType by its id.
     * 
     * @param id
     *            the id
     * @return the vacationType
     */
    VacationType getVacationTypeById(int id);

    /**
     * Update the given vacationType.
     * 
     * @param vacationType
     *            the vacationType to update
     */
    void updateVacationType(VacationType vacationType);
}
