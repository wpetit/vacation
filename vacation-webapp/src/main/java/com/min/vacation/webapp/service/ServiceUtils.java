package com.min.vacation.webapp.service;

import org.h2.util.StringUtils;

import com.min.vacation.business.model.SortType;

/**
 * The {@link ServiceUtils} class.
 * 
 * @author WPETIT
 */
public class ServiceUtils {

    /**
     * Constructor.
     */
    private ServiceUtils() {
        // private constructor.
    }

    /**
     * Convert the sortType to model type.
     * 
     * @param sortType
     *            the type to convert
     * @return the type converted
     */
    public static SortType convertSortType(final String sortType) {
        return SortType.valueOf(StringUtils.toUpperEnglish(sortType));
    }

}
