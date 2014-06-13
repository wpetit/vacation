package com.min.vacation.business.dao;

import java.util.List;

import com.min.vacation.business.model.VacationType;

public interface VacationTypeDao {

    /**
     * Save the given {@link VacationType}
     * 
     * @param vacationType
     *            the vacationType to save
     */
    public void save(VacationType vacationType);

    /**
     * Retrive the user with the given username.
     * 
     * @param username
     *            the user username
     * @return the user found
     */
    public List<VacationType> getUserVacationType(String username);

}
