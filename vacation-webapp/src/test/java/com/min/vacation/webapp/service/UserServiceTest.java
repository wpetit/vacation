/**
 * 
 */
package com.min.vacation.webapp.service;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.min.vacation.business.UserBusiness;
import com.min.vacation.model.User;

/**
 * The {@link UserServiceTest} class.
 * 
 * @author WPETIT
 * 
 */
@RunWith(JMockit.class)
public class UserServiceTest {

    @Tested
    private UserService userService;

    @Injectable
    private UserBusiness userBusiness;

    @Injectable
    private PasswordEncoder passwordEncoder;

    /**
     * Test method for
     * {@link com.min.vacation.webapp.service.UserService#createUser(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testCreateUser() {
        final String username = "username";
        final String password = "password";

        new Expectations() {
            {
                passwordEncoder.encode(password);
                result = "123";
                times = 1;
            }
        };

        userService.createUser(username, password);

        new Verifications() {
            {
                User user;
                userBusiness.createUser(user = withCapture());
                times = 1;
                assertEquals(username, user.getUsername());
                assertEquals("123", user.getPassword());
            }
        };
    }
}
