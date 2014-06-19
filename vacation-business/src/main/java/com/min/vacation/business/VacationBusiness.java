package com.min.vacation.business;

/**
 * The {@link VacationBusiness} class.
 * 
 * @author wpetit
 */
public interface VacationBusiness {
    /**
     * Return the number of vacation days not worked
     * 
     * @param username
     *            the username
     * @param vacationTypeId
     *            the vacation type
     * @return the number of vacation days not worked
     */
    int getVacationWorkingDaysCount(String username, int vacationTypeId);
}
