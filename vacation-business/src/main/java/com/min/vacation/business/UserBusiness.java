package com.min.vacation.business;

import com.min.vacation.model.User;

/**
 * The {@link UserBusiness} interface.
 * 
 * @author WPETIT
 * 
 */
public interface UserBusiness {
    /**
     * Find a user by its username.
     * 
     * @param username
     *            the username
     * @return the user
     */
    User getUserByUsername(String username);
}
