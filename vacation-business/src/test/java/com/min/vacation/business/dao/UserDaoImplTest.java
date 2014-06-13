package com.min.vacation.business.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext-dao.xml" })
public class UserDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetUserByUsername() {

    }

}
