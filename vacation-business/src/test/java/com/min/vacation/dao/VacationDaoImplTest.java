package com.min.vacation.dao;

import static org.junit.Assert.assertEquals;
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

import com.min.vacation.model.PaginatedModel;
import com.min.vacation.model.SortType;
import com.min.vacation.model.Vacation;

/**
 * The {@link VacationDaoImplTest} class.
 * 
 * @author wpetit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-dao.xml" })
@Transactional
public class VacationDaoImplTest {

    @Autowired
    private VacationDao vacationDao;

    @Test
    public void testGetUserVacationsCountWithOne() {
        assertEquals(0, vacationDao.getUserVacationsCount("wpetit0"));
    }

    @Test
    public void testGetUserVacationsCountWithZero() {
        assertEquals(1, vacationDao.getUserVacationsCount("wpetit1"));
    }

    @Test
    public void testFindUserVacations() {
        PaginatedModel<Vacation> paginatedVacation = vacationDao
                .findUserVacations("wpetit1", 0, 1, "from", SortType.DESC);
        assertEquals(1, paginatedVacation.getTotal());
        assertEquals(0, paginatedVacation.getStartIndex());
        List<Vacation> vacations = paginatedVacation.getModelList();
        assertEquals(1, vacations.size());
    }

    @Test
    public void testFindUserVacationsWithEmptySort() {
        PaginatedModel<Vacation> paginatedVacation = vacationDao
                .findUserVacations("wpetit1", 0, 1, null, null);
        assertEquals(1, paginatedVacation.getTotal());
        assertEquals(0, paginatedVacation.getStartIndex());
        List<Vacation> vacations = paginatedVacation.getModelList();
        assertEquals(1, vacations.size());
    }

    @Test
    public void testSaveVacation() {
        Date from = new Date();
        Date to = new Date(from.getTime() + 86400000);
        Vacation vacation = new Vacation();
        vacation.setFrom(from);
        vacation.setTo(to);
        vacationDao.save(vacation);

        Vacation vacationRetrieved = vacationDao.getVacationById(vacation
                .getId());
        assertEquals(
                0,
                Days.daysBetween(new Instant(from.getTime()),
                        new Instant(vacationRetrieved.getFrom().getTime()))
                        .getDays());
        assertEquals(
                0,
                Days.daysBetween(new Instant(to.getTime()),
                        new Instant(vacationRetrieved.getTo().getTime()))
                        .getDays());
    }

    @Test
    public void testDeleteVacation() {
        Vacation vacation = vacationDao.getVacationById(0);
        vacationDao.delete(vacation);
        Vacation vacationDeleted = vacationDao.getVacationById(0);
        assertNull(vacationDeleted);
    }

    @Test
    public void testGetVacationByUsernameAndType() {
        List<Vacation> vacationList = vacationDao.getVacationByUsernameAndType(
                "vacationTest", 1);
        assertEquals(1, vacationList.size());
        Vacation vacation = vacationList.get(0);
        DateTime from = new DateTime(2014, 7, 13, 0, 0);
        DateTime to = new DateTime(2014, 8, 8, 0, 0);
        assertEquals(
                0,
                Days.daysBetween(from,
                        new Instant(vacation.getFrom().getTime())).getDays());
        assertEquals(0,
                Days.daysBetween(to, new Instant(vacation.getTo().getTime()))
                        .getDays());
    }

    @Test
    public void testDeleteVacationVacationType() {
        List<Vacation> vacationList = vacationDao.getVacationByUsernameAndType(
                "vacationTest", 1);
        assertEquals(1, vacationList.size());
        vacationDao.deleteVacationByVacationType(1);
        List<Vacation> vacationListAfterDelete = vacationDao
                .getVacationByUsernameAndType("vacationTest", 0);
        assertEquals(0, vacationListAfterDelete.size());
    }

    @Test
    public void testUpdateVacation() {
        Vacation vacation = vacationDao.getVacationById(1);
        DateTime from = new DateTime(2014, 7, 14, 0, 0);
        DateTime to = new DateTime(2014, 8, 9, 0, 0);
        vacation.setFrom(new Date(from.getMillis()));
        vacation.setTo(new Date(to.getMillis()));
        vacationDao.update(vacation);
        Vacation vacationUpdated = vacationDao.getVacationById(1);
        assertEquals(
                0,
                Days.daysBetween(from,
                        new Instant(vacationUpdated.getFrom().getTime()))
                        .getDays());
        assertEquals(
                0,
                Days.daysBetween(to,
                        new Instant(vacationUpdated.getTo().getTime()))
                        .getDays());

    }
}
