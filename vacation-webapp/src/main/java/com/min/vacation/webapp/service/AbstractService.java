package com.min.vacation.webapp.service;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The {@link AbstractService} class.
 * 
 * @author wpetit
 */
public abstract class AbstractService {
    /**
     * Return the authenticated user name.
     * 
     * @return the username
     */
    public String getAuthenticatedUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        return ((org.springframework.security.core.userdetails.User) context
                .getAuthentication().getPrincipal()).getUsername();
    }
}
