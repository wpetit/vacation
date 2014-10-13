package com.min.vacation.webapp.service.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.UrlUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * The {@link ServicesRequestMatcher} class.
 * 
 * @author WPETIT
 * 
 */
public class ServicesRequestMatcher implements RequestMatcher {
    /** {@inheritDoc} */
    @Override
    public boolean matches(final HttpServletRequest request) {
        String url = UrlUtils.buildRequestUrl(request);
        return url.startsWith("/rest/");
    }
}
