package com.example.skip.RestaurantSuggestionService.service;

import com.example.skip.RestaurantSuggestionService.dto.RestaurantDTO;
import com.example.skip.RestaurantSuggestionService.exception.RestaurantServiceException;

import java.util.List;

/**
 * @author Punit Dhakan
 */
public interface RestaurantSuggestionService {

    public List<RestaurantDTO> fetchRestaurant(String latitude, String longitude, String radius) throws RestaurantServiceException;

    public List<RestaurantDTO> getRestaurantFromYelp(String term, String location) throws RestaurantServiceException;
}
