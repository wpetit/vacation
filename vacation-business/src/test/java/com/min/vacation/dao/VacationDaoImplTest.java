package com.min.vacation.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.vacation.dao.VacationDao;
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
}
