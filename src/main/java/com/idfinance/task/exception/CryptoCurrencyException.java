package com.idfinance.task.exception;

public class CryptoCurrencyException extends RuntimeException {
    public CryptoCurrencyException() {
    }

    public CryptoCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptoCurrencyException(String message) {
        super(message);
    }

    public CryptoCurrencyException(Throwable cause) {
        super(cause);
    }
}
