package com.zerobase.weather.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 예외 핸들러
 * @author 이희영
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Exception handleAllException() {
        System.out.println("error from GlobalExceptionHandler");
        return new Exception();
    }
}
