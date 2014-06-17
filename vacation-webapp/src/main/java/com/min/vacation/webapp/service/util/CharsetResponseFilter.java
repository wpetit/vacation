package com.min.vacation.webapp.service.util;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * The {@link CharsetResponseFilter} class.
 * 
 * @author wpetit
 */
@Provider
public class CharsetResponseFilter implements ContainerResponseFilter {
    /** {@inheritDoc} **/
    @Override
    public void filter(final ContainerRequestContext request,
            final ContainerResponseContext response) {
        MediaType type = response.getMediaType();
        if (type != null) {
            StringBuilder stringBuilder = new StringBuilder(type.toString());
            // if charset is not specified
            if (!type.toString().contains("charset")) {
                // set charset to utf-8
                stringBuilder.append(";charset=utf-8");
                response.getHeaders().putSingle("Content-Type",
                        stringBuilder.toString());
            }
        }
    }
}
