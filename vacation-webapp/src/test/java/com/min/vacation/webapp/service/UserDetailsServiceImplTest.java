/**
 * 
 */
package com.min.vacation.webapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.min.vacation.business.UserBusiness;
import com.min.vacation.model.User;

/**
 * The {@link UserDetailsServiceImplTest} class.
 * 
 * @author wpetit
 * 
 */
@RunWith(JMockit.class)
public class UserDetailsServiceImplTest {

    @Tested
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Injectable
    private UserBusiness userBusiness;

    /**
     * Test method for
     * {@link com.min.vacation.webapp.service.UserDetailsServiceImpl#loadUserByUsername(java.lang.String)}
     * .
     */
    @Test
    public void testLoadUserByUsername() {
        final User user = new User();
        user.setUsername("wpetit");
        user.setPassword("password");
        user.setRole("ROLE_USER");
        user.setId(0);

        new Expectations() {
            {
                userBusiness.getUserByUsername("wpetit");
                result = user;
            }
        };

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("wpetit");

        assertEquals("wpetit", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }

    /**
     * Test method for
     * {@link com.min.vacation.webapp.service.UserDetailsServiceImpl#loadUserByUsername(java.lang.String)}
     * with an invalid username.
     */
    @Test
    public void testLoadUserByUsernameUnknownUser() {

        new Expectations() {
            {
                userBusiness.getUserByUsername("wpetit");
                result = null;
            }
        };

        try {
            userDetailsServiceImpl.loadUserByUsername("wpetit");
            fail("Service should return a UsernameNotFoundException");
        } catch (UsernameNotFoundException e) {
            // Ok
        }
    }
}
