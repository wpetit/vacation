package com.min.vacation.business;

import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

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
}
