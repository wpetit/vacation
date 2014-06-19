package com.min.vacation.dao;

import java.util.List;

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
