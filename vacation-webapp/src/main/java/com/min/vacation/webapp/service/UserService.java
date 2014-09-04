package com.min.vacation.webapp.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.min.vacation.business.UserBusiness;

/**
 * The {@link UserService} class.
 * 
 * @author wpetit
 * 
 */
@Component
@Path("user")
public class UserService {
    /** The LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    /** The userBusiness. */
    @Autowired
    private UserBusiness userBusiness;

    /** The passwordEncoder. */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Create a user with role user and the username and password given.
     * 
     * @param username
     *            the username
     * @param password
     *            the password
     */
    @POST
    @Path("{username}")
    public void createUser(@PathParam("username") final String username, final String password) {
        LOG.debug("Create user {}", username);
        com.min.vacation.model.User user = new com.min.vacation.model.User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER");
        userBusiness.createUser(user);
    }
}
