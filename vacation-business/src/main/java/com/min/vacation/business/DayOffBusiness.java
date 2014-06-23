package com.min.vacation.business;

import java.util.Date;

/**
 * The {@link DayOffBusiness} interface.
 * 
 * @author wpetit
 */
public interface DayOffBusiness {
    /**
     * Check if the given date is a day off.
     * 
     * @param date
     *            the date
     * @return is a day off
     */
    boolean isDayOff(Date date);

}
