package com.min.vacation.business.dao;

import java.util.List;

import com.min.vacation.business.model.VacationType;

/**
 * The {@link VacationTypeDao} interface.
 * 
 * @author WPETIT
 * 
 */
public interface VacationTypeDao {

    /**
     * Save the given vacationType.
     * 
     * @param vacationType
     *            the vacationType to save.
     */
    public void save(VacationType vacationType);

    /**
     * Retrieve the user vacation types.
     * 
     * @param username
     *            the user username.
     * @return the vacation types.
     */
    public List<VacationType> getUserVacationType(final String username);

}
