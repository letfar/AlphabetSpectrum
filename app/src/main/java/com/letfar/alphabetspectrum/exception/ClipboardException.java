package com.letfar.alphabetspectrum.exception;

/**
 * Created by Alex on 07.09.2016.
 */
public class ClipboardException extends RuntimeException {
    public ClipboardException() {
    }

    public ClipboardException(String message) {
        super(message);
    }

    public ClipboardException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClipboardException(Throwable cause) {
        super(cause);
    }
}
