package com.min.vacation.business;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.util.ReflectionTestUtils;

import com.min.vacation.business.impl.DayOffBusinessImpl;
import com.min.vacation.business.impl.VacationBusinessImpl;
import com.min.vacation.dao.VacationDao;
import com.min.vacation.model.Vacation;

/**
 * The {@link VacationBusinessImplTest} class.
 * 
 * @author wpetit
 */
@RunWith(JMockit.class)
public class VacationBusinessImplTest {

    @Tested
    private VacationBusinessImpl vacationBusiness;

    @Injectable
    private VacationDao vacationDao;

    @Test
    public void testSaveVacation() {
        final Vacation vacation = new Vacation();
        vacationBusiness.save(vacation);

        new Verifications() {
            {
                vacationDao.save(vacation);
            }
        };
    }

    @Test
    public void testGetVacationWorkingDaysCount() {
        ReflectionTestUtils.setField(vacationBusiness, "dayOffBusiness",
                new DayOffBusinessImpl());
        final Vacation vacation = new Vacation();
        Calendar from = Calendar.getInstance();
        from.set(2014, 6, 13);
        Calendar to = Calendar.getInstance();
        to.set(2014, 7, 8);
        vacation.setFrom(from.getTime());
        vacation.setTo(to.getTime());

        new Expectations() {
            {
                vacationDao.getVacationByUsernameAndType("wpetit", 0);
                result = Arrays.asList(vacation);
            }
        };

        assertEquals(19,
                vacationBusiness.getVacationWorkingDaysCount("wpetit", 0));
    }

    @Test
    public void testGetVacationWorkingDaysCountWithStartAfterEnd() {
        ReflectionTestUtils.setField(vacationBusiness, "dayOffBusiness",
                new DayOffBusinessImpl());
        final Vacation vacation = new Vacation();
        Calendar from = Calendar.getInstance();
        from.set(2014, 5, 25);
        Calendar to = Calendar.getInstance();
        to.set(2014, 5, 21);
        vacation.setFrom(from.getTime());
        vacation.setTo(to.getTime());

        new Expectations() {
            {
                vacationDao.getVacationByUsernameAndType("wpetit", 0);
                result = Arrays.asList(vacation);
            }
        };

        assertEquals(0,
                vacationBusiness.getVacationWorkingDaysCount("wpetit", 0));
    }
}
