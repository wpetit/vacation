package com.min.vacation.business;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.min.vacation.business.impl.DayOffBusinessImpl;

/**
 * The {@link DayOffBusinessImplTest} class.
 * 
 * @author wpetit
 */
@RunWith(Parameterized.class)
public class DayOffBusinessImplTest {

    private final DayOffBusiness dayOffBusiness;
    private final int day;
    private final int month;
    private final int year;
    private final boolean expectedResult;

    public DayOffBusinessImplTest(final int day, final int month,
            final int year, final boolean expectedResult) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.expectedResult = expectedResult;
        this.dayOffBusiness = new DayOffBusinessImpl();
    }

    @Parameterized.Parameters(name = "test(testIsDayOff : {0}/{1}/{2} => {3}))")
    public static List<Object[]> getStringSortParameters() {
        return Arrays.asList(new Object[][] {
                // Monday not day off
                { 23, 6, 2014, false },
                // Sunday day off
                { 22, 5, 2014, true },
                // Saturday day off
                { 21, 5, 2014, true },
                // 01/01 day off
                { 1, 0, 2014, true },
                // Easter monday
                { 21, 3, 2014, true },
                // Easter monday
                { 1, 3, 2013, true },
                // Easter monday
                { 9, 3, 2012, true },
                // Easter monday
                { 25, 3, 2011, true },
                // 01/05
                { 1, 4, 2014, true },
                // 08/05
                { 8, 4, 2014, true },
                // Ascension
                { 29, 4, 2014, true },
                // Pentecost monday
                { 9, 5, 2014, true },
                // 14/07
                { 14, 6, 2014, true },
                // 15/08
                { 15, 7, 2014, true },
                // 01/11
                { 1, 10, 2014, true },
                // 11/11
                { 11, 10, 2014, true },
                // 25/12
                { 25, 12, 2014, true } });
    }

    @Test
    public void testIsDayOff() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        assertEquals(expectedResult,
                dayOffBusiness.isDayOff(calendar.getTime()));
    }

}
