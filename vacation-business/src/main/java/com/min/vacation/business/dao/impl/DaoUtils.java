package com.min.vacation.business.dao.impl;

import com.min.vacation.business.model.SortType;

/**
 * The {@link DaoUtils} class.
 * 
 * @author wpetit
 */
public final class DaoUtils {

    /**
     * Constructor.
     */
    private DaoUtils() {
        // private constructor
    }

    /**
     * Convert sortType to sql sort string.
     * 
     * @param sortType
     *            the sortType to convert.
     * @return the sql string
     */
    public static String getStringSort(final SortType sortType,
            final SortType defaultSortType) {
        SortType finalSortType = sortType;
        String sort = "desc";
        if (sortType == null) {
            finalSortType = defaultSortType;
        }
        if (finalSortType != null) {
            switch (finalSortType) {
            case ASC:
                sort = "asc";
                break;
            case DESC:
            default:
                sort = "desc";
                break;
            }
        }
        return sort;
    }
}
