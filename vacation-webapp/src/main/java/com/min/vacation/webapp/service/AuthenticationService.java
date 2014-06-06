package com.min.vacation.webapp.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

@Component
@Path("authentication")
public class AuthenticationService {
    @POST
    public Response authentication(@FormParam("username") final String username,
            @FormParam("password") final String password) {
        if("wpetit".equals(username) ) {
            return Response.ok().build();
        } else {
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }
}
