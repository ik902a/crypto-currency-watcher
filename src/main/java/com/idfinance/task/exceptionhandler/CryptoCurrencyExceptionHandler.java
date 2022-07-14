package com.idfinance.task.exceptionhandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.idfinance.task.exception.CryptoCurrencyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class CryptoCurrencyExceptionHandler {

    @ExceptionHandler(CryptoCurrencyException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionInformation cryptoCurrencyExceptionHandler(CryptoCurrencyException exception) {
        log.error(exception.getMessage(), exception);
        return new ExceptionInformation(
                exception.getClass().getSimpleName(),
                NOT_FOUND,
                exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionInformation methodArgumentTypeMismatchExceptionHandler(
            MethodArgumentTypeMismatchException exception) {
        log.error(exception.getMessage(), exception);
        return new ExceptionInformation(
                exception.getClass().getSimpleName(),
                BAD_REQUEST,
                exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionInformation constraintViolationExceptionHandler(ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);
        return new ExceptionInformation(
                exception.getClass().getSimpleName(),
                BAD_REQUEST,
                exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionInformation exceptionHandler(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ExceptionInformation(
                exception.getClass().getSimpleName(),
                INTERNAL_SERVER_ERROR,
                exception.getMessage());
    }
}
