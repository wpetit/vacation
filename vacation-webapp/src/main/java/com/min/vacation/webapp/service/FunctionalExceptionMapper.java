package com.min.vacation.webapp.service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.min.vacation.exception.FunctionalException;

/**
 * The {@link FunctionalExceptionMapper} class.
 * 
 * @author WPETIT
 * 
 */
@Provider
public class FunctionalExceptionMapper implements ExceptionMapper<FunctionalException> {

    /** {@inheritDoc} */
    @Override
    public Response toResponse(final FunctionalException exception) {
        return Response.status(Status.FORBIDDEN).entity(exception.getMessage())
                .type(MediaType.APPLICATION_JSON).build();
    }
}
