package com.min.vacation.webapp.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The {@link AuthenticationService} class.
 * 
 * @author wpetit
 */
@Component
@Path("authentication")
public class AuthenticationService {

    /** The LOG. */
    private static final Logger LOG = LoggerFactory
            .getLogger(AuthenticationService.class);

    /**
     * Authenticate the given user.
     * 
     * @param username
     *            the user name
     * @param password
     *            the user password
     * @return if the user is successfully authenticated
     */
    @POST
    public boolean authenticate(@FormParam("username") final String username,
            @FormParam("password") final String password) {
        LOG.debug("Authenticating : {}", username);
        boolean authenticated = "wpetit".equals(username);
        LOG.debug("User {} authentication result : {}", username, authenticated);
        return authenticated;
    }
}
