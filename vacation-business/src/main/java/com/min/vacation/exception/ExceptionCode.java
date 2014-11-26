package com.min.vacation.exception;

/**
 * The {@link ExceptionCode} class.
 * 
 * @author WPETIT
 * 
 */
public enum ExceptionCode {
    TOO_MANY_VACATION_FOR_TYPE("001"), VACATION_PERIOD_NOT_IN_TYPE_PERIOD("002");

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
