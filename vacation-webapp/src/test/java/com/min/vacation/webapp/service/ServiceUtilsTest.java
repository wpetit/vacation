/**
 * 
 */
package com.min.vacation.webapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.min.vacation.model.SortType;

/**
 * The {@link ServiceUtilsTest} class.
 * 
 * @author WPETIT
 * 
 */
@RunWith(Parameterized.class)
public class ServiceUtilsTest {

    private final String sortType;
    private final SortType expectedResult;
    private final boolean shouldThrowException;

    public ServiceUtilsTest(final String sortType, final SortType expectedResult,
            final boolean shouldThrowException) {
        this.sortType = sortType;
        this.expectedResult = expectedResult;
        this.shouldThrowException = shouldThrowException;
    }

    @Parameterized.Parameters(name = "test(testConvertSortType : {0} => {1} || Exception : {2}))")
    public static List<Object[]> getStringSortParameters() {
        return Arrays.asList(new Object[][] { { "asc", SortType.ASC, false },
                { "ASC", SortType.ASC, false }, { "desc", SortType.DESC, false },
                { "DESC", SortType.DESC, false }, { "nonValid", SortType.DESC, true } });
    }

    /**
     * Test method for
     * {@link com.min.vacation.webapp.service.ServiceUtils#convertSortType(java.lang.String)}.
     */
    @Test
    public void testConvertSortType() {
        if (shouldThrowException) {
            try {
                ServiceUtils.convertSortType(sortType);
                fail("Non valid sort should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        } else {
            assertEquals(expectedResult, ServiceUtils.convertSortType(sortType));
        }
    }

}
