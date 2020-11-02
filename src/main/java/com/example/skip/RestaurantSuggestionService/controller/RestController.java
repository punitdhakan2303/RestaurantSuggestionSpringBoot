package com.example.skip.RestaurantSuggestionService.controller;

import com.example.skip.RestaurantSuggestionService.dto.RestaurantDTO;
import com.example.skip.RestaurantSuggestionService.exception.RestaurantServiceException;
import com.example.skip.RestaurantSuggestionService.service.RestaurantSuggestionServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
public class RestController {


    @RequestMapping(value = "/google/restaurants", method = RequestMethod.GET)
    public List<RestaurantDTO> fetchRestaurant(@RequestParam(value="latitude") String latitude,
                                               @RequestParam(value="longitude") String longitude,
                                               @RequestParam(value="radius") String radius) throws RestaurantServiceException {

        RestaurantSuggestionServiceImpl serviceImpl = new RestaurantSuggestionServiceImpl();

        if(latitude == null || longitude == null || radius == null) {
            throw new NullPointerException(" Please enter latitude, longitude, radius in order to fetch the restaurants ");
        }else{
            return serviceImpl.fetchRestaurant(latitude, longitude, radius);
        }
    }


    @RequestMapping(value = "/yelp/restaurants", method = RequestMethod.GET)
    public List<RestaurantDTO> getRestaurant(@RequestParam(value="term", required=false) String term,
                                               @RequestParam(value="location", required=false) String location) throws RestaurantServiceException {


        RestaurantSuggestionServiceImpl serviceImpl = new RestaurantSuggestionServiceImpl();

        if(term == null || location == null ) {
            throw new RestaurantServiceException(" Please enter term and location in order to fetch the restaurants ");
        }else{
            return serviceImpl.getRestaurantFromYelp(term, location);
        }


    }

}
