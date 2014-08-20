package com.min.vacation.webapp.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.min.vacation.business.UserBusiness;

/**
 * The {@link UserDetailsService} class. Retrieves the user from its username.
 * 
 * @author wpetit
 * 
 */
@Component
public class UserDetailsService implements
        org.springframework.security.core.userdetails.UserDetailsService {

    /** The userDao. */
    @Autowired
    private UserBusiness userBusiness;

    /** {@inheritDoc} */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        com.min.vacation.model.User user = userBusiness.getUserByUsername(username);
        UserDetails userDetails = null;
        if (user != null) {
            userDetails = new User(username, user.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
        } else {
            throw new UsernameNotFoundException("User " + username + " has not been found.");
        }
        return userDetails;
    }

}
