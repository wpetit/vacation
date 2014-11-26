package com.min.vacation.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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
import com.min.vacation.dao.VacationTypeDao;
import com.min.vacation.exception.FunctionalException;
import com.min.vacation.model.Vacation;
import com.min.vacation.model.VacationType;

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

    @Injectable
    private VacationTypeDao vacationTypeDao;

    // TODO add test in case the nb days of the vacation exceeds the vacation type nb days
    // TODO gérer en dehors des dates de vacation type
    @Test
    public void testSaveVacation() {
        final Vacation vacation = new Vacation();
        try {
            vacationBusiness.save(vacation);

            new Verifications() {
                {
                    vacationDao.save(vacation);
                }
            };
        } catch (FunctionalException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSaveVacationType() {
        final VacationType vacationType = new VacationType();
        vacationBusiness.save(vacationType);

        new Verifications() {
            {
                vacationTypeDao.save(vacationType);
            }
        };
    }

    @Test
    public void testGetVacation() {
        final Vacation vacation = new Vacation();

        new Expectations() {
            {
                vacationDao.getVacationById(2);
                result = vacation;
            }
        };

        assertEquals(vacation, vacationBusiness.getVacation(2));
    }

    @Test
    public void testGetVacationByUsernameAndType() {
        final Vacation vacation = new Vacation();
        final List<Vacation> vacationList = Arrays.asList(vacation);

        new Expectations() {
            {
                vacationDao.getVacationByUsernameAndType("wpetit", 2);
                result = vacationList;
            }
        };

        assertEquals(vacationList, vacationBusiness.getVacationByUsernameAndType("wpetit", 2));
    }

    @Test
    public void testVacationTypeById() {
        final VacationType vacationType = new VacationType();

        new Expectations() {
            {
                vacationTypeDao.getVacationTypeById(2);
                result = vacationType;
            }
        };

        assertEquals(vacationType, vacationBusiness.getVacationTypeById(2));
    }

    @Test
    public void testGetVacationWorkingDaysCount() {
        ReflectionTestUtils.setField(vacationBusiness, "dayOffBusiness", new DayOffBusinessImpl());
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

        assertEquals(19, vacationBusiness.getVacationWorkingDaysCount("wpetit", 0));
    }

    @Test
    public void testGetVacationWorkingDaysCountWithStartAfterEnd() {
        ReflectionTestUtils.setField(vacationBusiness, "dayOffBusiness", new DayOffBusinessImpl());
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

        assertEquals(0, vacationBusiness.getVacationWorkingDaysCount("wpetit", 0));
    }

    @Test
    public void testDeleteVacation() {
        vacationBusiness.deleteVacation(2);
        new Verifications() {
            {
                vacationDao.delete((Vacation) any);
            }
        };
    }

    @Test
    public void testDeleteVacationType() {
        vacationBusiness.deleteVacationType(2);
        new Verifications() {
            {
                vacationDao.deleteVacationByVacationType(2);
                vacationTypeDao.delete((VacationType) any);
            }
        };
    }

    // TODO add test in case the nb days of the vacation exceeds the vacation type nb days
    // TODO gérer en dehors des dates de vacation type
    @Test
    public void testUpdateVacation() {
        try {
            final Vacation vacation = new Vacation();
            VacationType vacationType = new VacationType();
            vacationType.setId(3);
            vacation.setType(vacationType);
            vacationBusiness.updateVacation(vacation);
            new Verifications() {
                {
                    vacationTypeDao.getVacationTypeById(3);
                    vacationDao.update(vacation);
                }
            };
        } catch (FunctionalException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateVacationType() {
        final VacationType vacationType = new VacationType();
        vacationType.setId(3);
        vacationBusiness.updateVacationType(vacationType);
        new Verifications() {
            {
                vacationTypeDao.update(vacationType);
            }
        };
    }
}
