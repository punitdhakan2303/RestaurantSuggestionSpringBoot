package com.example.skip.RestaurantSuggestionService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Punit Dhakan
 */
@ControllerAdvice
public class RestaurantSuggestionServiceExceptionHandler {

    @ExceptionHandler(value = {RestaurantServiceException.class})
    public ResponseEntity<Object> handleRestaurantException(RestaurantServiceException exception){

        HttpStatus badStatus = HttpStatus.BAD_REQUEST;
        RestaurantError error = new RestaurantError(
                exception.getMessage(),
                exception,
                badStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(error,badStatus);
    }

}
