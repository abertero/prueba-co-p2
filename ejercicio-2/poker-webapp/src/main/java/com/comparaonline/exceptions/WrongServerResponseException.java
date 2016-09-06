package com.comparaonline.exceptions;

public class WrongServerResponseException extends Exception {

    public WrongServerResponseException() {
    }

    public WrongServerResponseException(String message) {
        super(message);
    }

    public WrongServerResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongServerResponseException(Throwable cause) {
        super(cause);
    }

    public WrongServerResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
