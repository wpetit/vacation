package com.min.vacation.business.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.min.vacation.business.DayOffBusiness;

/**
 * The {@link DayOffBusinessImpl} class.
 * 
 * @author wpetit
 */
@Component
public class DayOffBusinessImpl implements DayOffBusiness {

    /** The NOVEMBER_11TH_DAY. */
    private static final int NOVEMBER_11TH_DAY = 11;
    /** The JANUARY_1ST_DAY. */
    private static final int JANUARY_1ST_DAY = 1;
    /** The NOVEMBER_1ST_DAY. */
    private static final int NOVEMBER_1ST_DAY = 1;
    /** The AUGUST_15TH_DAY. */
    private static final int AUGUST_15TH_DAY = 15;
    /** The JULY_14TH_DAY. */
    private static final int JULY_14TH_DAY = 14;
    /** The MAY_8TH_DAY. */
    private static final int MAY_8TH_DAY = 8;
    /** The MAY_1ST_DAY. */
    private static final int MAY_1ST_DAY = 1;
    /** The CHRISTMAS_DAY. */
    private static final int CHRISTMAS_DAY = 25;

    /** {@inheritDoc} **/
    @Override
    public boolean isDayOff(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return isFixedDayOff(calendar) || isWeekend(calendar)
                || isAscension(calendar) || isEasterMonday(calendar)
                || isPentecost(calendar);
    }

    /**
     * Check if the date is a fixed day off (e.g 14/07).
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isFixedDayOff(final Calendar date) {
        return isChristmasDay(date) || isAugustFifteenth(date)
                || isJanuaryFirst(date) || isJulyFourteenth(date)
                || isMayFirst(date) || isMayEighth(date)
                || isNovemberFirst(date) || isNovemberEleventh(date);

    }

    /**
     * Check if the date is the Christmas day.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isChristmasDay(final Calendar date) {
        return CHRISTMAS_DAY == date.get(Calendar.DAY_OF_MONTH)
                && Calendar.DECEMBER == date.get(Calendar.MONTH);
    }

    /**
     * Check if the date is 01/05.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isMayFirst(final Calendar date) {
        return MAY_1ST_DAY == date.get(Calendar.DAY_OF_MONTH)
                && Calendar.MAY == date.get(Calendar.MONTH);
    }

    /**
     * Check if the date is 08/05.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isMayEighth(final Calendar date) {
        return MAY_8TH_DAY == date.get(Calendar.DAY_OF_MONTH)
                && Calendar.MAY == date.get(Calendar.MONTH);
    }

    /**
     * Check if the date is 14/07.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isJulyFourteenth(final Calendar date) {
        return JULY_14TH_DAY == date.get(Calendar.DAY_OF_MONTH)
                && Calendar.JULY == date.get(Calendar.MONTH);
    }

    /**
     * Check if the date is 15/08.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isAugustFifteenth(final Calendar date) {
        return AUGUST_15TH_DAY == date.get(Calendar.DAY_OF_MONTH)
                && Calendar.AUGUST == date.get(Calendar.MONTH);
    }

    /**
     * Check if the date is 1/11.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isNovemberFirst(final Calendar date) {
        return NOVEMBER_1ST_DAY == date.get(Calendar.DAY_OF_MONTH)
                && Calendar.NOVEMBER == date.get(Calendar.MONTH);
    }

    /**
     * Check if the date is 11/11.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isNovemberEleventh(final Calendar date) {
        return NOVEMBER_11TH_DAY == date.get(Calendar.DAY_OF_MONTH)
                && Calendar.NOVEMBER == date.get(Calendar.MONTH);
    }

    /**
     * Check if the date is 01/01.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isJanuaryFirst(final Calendar date) {
        return JANUARY_1ST_DAY == date.get(Calendar.DAY_OF_MONTH)
                && Calendar.JANUARY == date.get(Calendar.MONTH);
    }

    /**
     * Get the easter date fot the given year.
     * 
     * @param year
     *            the year.
     * @return if is easter day.
     */
    private Calendar getEasterDate(final int year) {

        double g = year % 19;

        int c = year / 100;
        int c4 = c / 4;
        int e = ((8 * c) + 13) / 25;

        int h = (int) (((19 * g) + c - c4 - e + 15) % 30);
        int k = h / 28;
        int p = 29 / (h + 1);
        int q = (int) ((21 - g) / 11);
        int i = (k * p * q - 1) * k + h;
        int b = (year / 4) + year;
        int j1 = b + i + 2 + c4 - c;
        int j2 = j1 % 7;
        int r = 28 + i - j2;

        int monthNumber = 4;
        int dayNumber = r - 31;
        boolean negativeDayNumber = dayNumber <= 0;

        if (negativeDayNumber) {
            monthNumber = 3;
            dayNumber = r;
        }

        Calendar paques = Calendar.getInstance();
        paques.set(year, monthNumber - 1, dayNumber);
        return paques;
    }

    /**
     * Check if the date is in week-end.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isWeekend(final Calendar date) {
        return Calendar.SATURDAY == date.get(Calendar.DAY_OF_WEEK)
                || Calendar.SUNDAY == date.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Check if the date is Easter Monday.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isEasterMonday(final Calendar date) {
        Calendar paquesMonday = getEasterDate(date.get(Calendar.YEAR));
        paquesMonday.add(Calendar.DAY_OF_YEAR, 1);
        return date.get(Calendar.DAY_OF_YEAR) == paquesMonday
                .get(Calendar.DAY_OF_YEAR)
                && date.get(Calendar.MONTH) == paquesMonday.get(Calendar.MONTH);
    }

    /**
     * Check if the date is Pentecost.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isPentecost(final Calendar date) {
        Calendar paquesMonday = getEasterDate(date.get(Calendar.YEAR));
        paquesMonday.add(Calendar.DAY_OF_YEAR, 50);
        return date.get(Calendar.DAY_OF_YEAR) == paquesMonday
                .get(Calendar.DAY_OF_YEAR)
                && date.get(Calendar.MONTH) == paquesMonday.get(Calendar.MONTH);
    }

    /**
     * Check if the date is Ascension.
     * 
     * @param date
     *            the date to check
     * @return if is day off
     */
    private boolean isAscension(final Calendar date) {
        Calendar paquesMonday = getEasterDate(date.get(Calendar.YEAR));
        paquesMonday.add(Calendar.DAY_OF_YEAR, 39);
        return date.get(Calendar.DAY_OF_YEAR) == paquesMonday
                .get(Calendar.DAY_OF_YEAR)
                && date.get(Calendar.MONTH) == paquesMonday.get(Calendar.MONTH);
    }
}
