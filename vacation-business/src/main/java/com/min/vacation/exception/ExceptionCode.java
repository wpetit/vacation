package com.min.vacation.exception;

/**
 * The {@link ExceptionCode} class.
 *
 * @author WPETIT
 *
 */
public enum ExceptionCode {
    TOO_MANY_VACATION_FOR_TYPE("001"),
    VACATION_PERIOD_NOT_IN_TYPE_PERIOD("002"),
    VACATION_ALREADY_EXISTS_IN_THIS_PERIOD_FOR_THIS_TYPE("003");

    /** The code. */
    private String code;

    /**
     * Constructor.
     *
     * @param code
     *            the code
     */
    private ExceptionCode(final String code) {
        this.code = code;
    }

    /**
     * Return the ExceptionCode code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }
}
