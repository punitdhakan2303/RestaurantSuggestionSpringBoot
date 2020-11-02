package com.example.skip.RestaurantSuggestionService.exception;

/**
 * @author  Punit Dhakan
 */
public class RestaurantServiceException extends RuntimeException{

    /**
     *
     * @param errorMessage
     */
    public RestaurantServiceException(String errorMessage) {
        super(errorMessage);
    }

    /**
     *
     * @param errorMessage
     * @param throwable
     */
    public RestaurantServiceException(String errorMessage, Throwable throwable){
        super(errorMessage,throwable);
    }
}
