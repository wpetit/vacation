package com.min.vacation.business.dao;

import com.min.vacation.business.model.User;

/**
 * The {@link UserDao} interface.
 * 
 * @author WPETIT
 */
public interface UserDao {

    /**
     * Save the given user.
     * 
     * @param user
     *            the user to save.
     */
    void save(User user);

    /**
     * Retrieve the user with the given username.
     * 
     * @param username
     *            the username
     * @return the user
     */
    User getUserByUsername(String username);

}
