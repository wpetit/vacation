package com.min.vacation.exception;

/**
 * The {@link FunctionalException} class.
 * 
 * @author WPETIT
 * 
 */
public class FunctionalException extends Exception {
    public FunctionalException(final String message) {
        super(message);
    }

    public FunctionalException(final Throwable cause) {
        super(cause);
    }

    public FunctionalException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
