package com.min.vacation.webapp.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("authentication")
public class AuthenticationService {
    @POST
    public boolean authenticate(@FormParam("username") final String username,
            @FormParam("password") final String password) {
        if ("wpetit".equals(username)) {
            return true;
        } else {
            return false;
        }
    }
}
