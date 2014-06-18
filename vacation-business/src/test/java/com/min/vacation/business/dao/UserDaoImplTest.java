package com.min.vacation.business.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.vacation.business.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-dao.xml" })
public class UserDaoImplTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("wpetit-save");
        userDao.save(user);

        User userRetrieved = userDao.getUserByUsername("wpetit-save");
        assertNotNull(userRetrieved);
        assertNotNull(userRetrieved.getId());
        assertEquals("wpetit-save", userRetrieved.getUsername());
    }

    @Test
    public void testGetUserByUsernameWithValidUsername() {
        User user = userDao.getUserByUsername("wpetit0");
        assertNotNull(user);
        assertEquals(0, user.getId());
        assertEquals("wpetit0", user.getUsername());
    }

    @Test
    public void testGetUserByUsernameWithInvalidUsername() {
        User user = userDao.getUserByUsername("wpetit-invalid");
        assertNull(user);
    }

}
