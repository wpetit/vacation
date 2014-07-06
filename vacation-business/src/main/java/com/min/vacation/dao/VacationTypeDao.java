package com.min.vacation.dao;

import java.util.List;

import com.min.vacation.model.SortType;
import com.min.vacation.model.VacationType;

/**
 * The {@link VacationTypeDao} interface.
 * 
 * @author wpetit
 */
public interface VacationTypeDao {

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
     * @param sortAttribute
     *            the attribute to sort on (default to "type" attribute)
     * @param sortType
     *            the sort operator (default : asc)
     * @return the user found
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
    void update(VacationType vacationType);

    /**
     * Delete the vacation type given.
     * 
     * @param vacationType
     *            the vacationType
     */
    void delete(VacationType vacationType);

}
