package ru.duzhinsky.lockbox.exception;

public class LockboxException extends RuntimeException {

    public LockboxException() {
    }

    public LockboxException(String message) {
        super(message);
    }

    public LockboxException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockboxException(Throwable cause) {
        super(cause);
    }

    public LockboxException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
