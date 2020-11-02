package com.example.skip.RestaurantSuggestionService.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @author Punit Dhakan
 */
public class RestaurantError {

    private final String errorMessage;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime dateTime;

    public RestaurantError(String errorMessage, Throwable throwable, HttpStatus httpStatus, ZonedDateTime dateTime) {
        this.errorMessage = errorMessage;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

}
