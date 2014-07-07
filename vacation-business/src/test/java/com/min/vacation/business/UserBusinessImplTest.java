package com.min.vacation.business;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.min.vacation.business.impl.UserBusinessImpl;
import com.min.vacation.dao.UserDao;
import com.min.vacation.model.User;

/**
 * Test the {@link UserBusinessImplTest}.
 * 
 * @author wpetit
 */
@RunWith(JMockit.class)
public class UserBusinessImplTest {

    @Tested
    private UserBusinessImpl userBusiness;

    @Injectable
    private UserDao userDao;

    @Test
    public void testGetUserByUsername() {
        final User user = new User();
        user.setUsername("wpetit");

        new Expectations() {
            {
                userDao.getUserByUsername("wpetit");
                result = user;
            }
        };

        assertEquals(user, userBusiness.getUserByUsername("wpetit"));
    }
}
