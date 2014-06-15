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
            String contentType = type.toString();
            if (!contentType.contains("charset")) {
                contentType = contentType + ";charset=utf-8";
                response.getHeaders().putSingle("Content-Type", contentType);
            }
        }
    }
}
