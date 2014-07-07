package com.min.vacation.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Instant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.vacation.model.User;
import com.min.vacation.model.VacationType;

/**
 * The {@link VacationTypeDaoImplTest} class.
 * 
 * @author wpetit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-dao.xml" })
@Transactional
public class VacationTypeDaoImplTest {

    /** The vacationTypeDao. */
    @Autowired
    private VacationTypeDao vacationTypeDao;

    /** The userDao. */
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
                .getUserVacationType("wpetit0", null, null);
        assertEquals(1, vacationTypeList.size());
        VacationType vacationTypeResult = vacationTypeList.get(0);
        assertEquals("Congés payés", vacationTypeResult.getType());
        assertNotNull(vacationTypeResult.getUser());
        assertEquals("wpetit0", vacationTypeResult.getUser().getUsername());
    }

    @Test
    public void testGetEmptyUserVacationType() {
        List<VacationType> vacationTypeList = vacationTypeDao
                .getUserVacationType("wpetit-noVacation", null, null);
        assertEquals(0, vacationTypeList.size());
    }

    @Test
    public void testDeleteVacationType() {
        VacationType vacationType = vacationTypeDao.getVacationTypeById(0);
        vacationTypeDao.delete(vacationType);
        assertNull(vacationTypeDao.getVacationTypeById(0));
    }

    @Test
    public void testUpdateVacationType() {
        VacationType vacationType = vacationTypeDao.getVacationTypeById(0);
        DateTime begin = new DateTime(2014, 6, 3, 0, 0);
        DateTime end = new DateTime(2014, 6, 4, 0, 0);
        vacationType.setBeginDate(new Date(begin.getMillis()));
        vacationType.setEndDate(new Date(end.getMillis()));
        vacationTypeDao.update(vacationType);
        vacationTypeDao.update(vacationType);
        VacationType vacationTypeUpdated = vacationTypeDao
                .getVacationTypeById(0);
        assertEquals(
                0,
                Days.daysBetween(
                        begin,
                        new Instant(vacationTypeUpdated.getBeginDate()
                                .getTime())).getDays());
        assertEquals(
                0,
                Days.daysBetween(end,
                        new Instant(vacationTypeUpdated.getEndDate().getTime()))
                        .getDays());
    }
}
