package com.min.vacation.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.vacation.business.UserBusiness;
import com.min.vacation.dao.UserDao;
import com.min.vacation.model.User;

/**
 * The {@link UserBusinessImpl} class.
 * 
 * @author WPETIT
 * 
 */
@Component
public class UserBusinessImpl implements UserBusiness {

    /** The userDao. */
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUsername(final String username) {
        return userDao.getUserByUsername(username);
    }

}
