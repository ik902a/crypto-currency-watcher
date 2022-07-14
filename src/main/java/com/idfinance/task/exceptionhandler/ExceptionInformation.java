package com.idfinance.task.exceptionhandler;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ExceptionInformation {
    String exception;
    HttpStatus status;
    String message;
}
