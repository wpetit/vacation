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
     * Return the number of vacation days not worked
     * 
     * @param username
     *            the username
     * @param vacationTypeId
     *            the vacation type
     * @return the number of vacation days not worked
     */
    int getVacationWorkingDaysCount(String username, int vacationTypeId);

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
    PaginatedModel<Vacation> findUserVacations(String username, int startIndex, int pageSize,
            String sortAttribute, SortType sortType);

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
    List<Vacation> getVacationByUsernameAndType(final String username, final int vacationTypeId);

    /**
     * Save the given {@link VacationType}.
     * 
     * @param vacationType
     *            the vacationType to save
     */
    void save(VacationType vacationType);

    /**
     * Retrieve the user with the given username.
     * 
     * @param username
     *            the user username
     * @return the user found
     */
    List<VacationType> getUserVacationType(String username);

    /**
     * Retrieve a vacationType by its id.
     * 
     * @param id
     *            the id
     * @return the vacationType
     */
    VacationType getVacationTypeById(int id);
}
