package com.min.vacation.business.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.min.vacation.business.dao.impl.DaoUtils;
import com.min.vacation.business.model.SortType;

/**
 * The {@link DaoUtilsTest} class.
 * 
 * @author wpetit
 */
@RunWith(Parameterized.class)
public class DaoUtilsTest {
    private final SortType inputSortType;
    private final SortType inputDefaultSortType;
    private final String expectedResult;

    /**
     * Constructor.
     * 
     * @param inputSortType
     *            the inputSortType
     * @param inputDefaultSortType
     *            the inputDefaultSortType
     * @param expectedResult
     *            the expectedResult
     */
    public DaoUtilsTest(final SortType inputSortType,
            final SortType inputDefaultSortType, final String expectedResult) {
        super();
        this.inputSortType = inputSortType;
        this.inputDefaultSortType = inputDefaultSortType;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters(name = "test(getStringSort : {0},{1} => {2}))")
    public static List<Object[]> getStringSortParameters() {
        return Arrays.asList(new Object[][] { { null, SortType.ASC, "asc" },
                { null, SortType.DESC, "desc" }, { SortType.ASC, null, "asc" },
                { SortType.DESC, null, "desc" },
                { SortType.ASC, SortType.DESC, "asc" },
                { SortType.DESC, SortType.ASC, "desc" },
                { SortType.ASC, SortType.ASC, "asc" },
                { SortType.DESC, SortType.DESC, "desc" },
                { null, null, "desc" }, });
    }

    @Test
    public void testGetStringSort() {
        assertEquals(expectedResult,
                DaoUtils.getStringSort(inputSortType, inputDefaultSortType));
    }

}
