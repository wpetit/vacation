package com.min.vacation.business.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.vacation.business.model.User;
import com.min.vacation.business.model.VacationType;

/**
 * The {@link VacationTypeDaoImplTest} class.
 * 
 * @author wpetit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-dao.xml" })
public class VacationTypeDaoImplTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private VacationTypeDao vacationTypeDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveVacationType() {
        User user = userDao.getUserByUsername("wpetit0");
        VacationType vacationType = new VacationType();
        vacationType.setType("Congés payés");
        vacationType.setUser(user);
        vacationTypeDao.save(vacationType);

        List<VacationType> vacationTypeList = vacationTypeDao
                .getUserVacationType("wpetit0");
        assertEquals(1, vacationTypeList.size());
        VacationType vacationTypeResult = vacationTypeList.get(0);
        assertEquals("Congés payés", vacationTypeResult.getType());
        assertNotNull(vacationTypeResult.getUser());
        assertEquals("wpetit0", vacationTypeResult.getUser().getUsername());
    }

    @Test
    public void testGetEmptyUserVacationType() {
        List<VacationType> vacationTypeList = vacationTypeDao
                .getUserVacationType("wpetit-noVacation");
        assertEquals(0, vacationTypeList.size());
    }
}
