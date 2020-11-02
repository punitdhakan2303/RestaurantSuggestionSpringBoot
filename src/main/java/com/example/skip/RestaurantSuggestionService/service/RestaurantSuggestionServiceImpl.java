package com.example.skip.RestaurantSuggestionService.service;

import com.example.skip.RestaurantSuggestionService.dto.RestaurantDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.example.skip.RestaurantSuggestionService.exception.RestaurantServiceException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Punit Dhakan
 */
public class RestaurantSuggestionServiceImpl implements RestaurantSuggestionService{

    //Google
    private static final String GOOGLE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private static final String GOOGLE_API_KEY = "AIzaSyBJlPRGXVLkw2Mn09sMwQhGv4nX9V-5UR4";
    private static final String TYPE = "restaurant";
    private static final String OPEN = "Opened";
    private static final String CLOSED = "Closed";


    //Yelp
    private static final String YELP_URL = "https://api.yelp.com/v3/businesses/search";
    private static final String YELP_API_KEY = "PBVxrLrBRGFR5NOnbsqwrK5nBtRMIqxX6qyzZ_LOsFm1-pj59A9OeOQcrtZ4QueLeL1IuArmNKJeWXs1KgsURC25GUWPUDUGPirCLrCKgiK9SM5cSQQZkvFknXmdX3Yx";


    public List<RestaurantDTO> fetchRestaurant(String latitude, String longitude, String radius) throws RestaurantServiceException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GOOGLE_URL)
                .queryParam("location", latitude + "," + longitude)
                .queryParam("radius", radius)
                .queryParam("types", TYPE)
                .queryParam("key", RestaurantSuggestionServiceImpl.GOOGLE_API_KEY);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        final String body = response.getBody();

//        //Convert String to Json Object
        JSONObject jsonObj = new JSONObject(body);
        JSONArray results = jsonObj.getJSONArray("results");

        List<RestaurantDTO> finalResultList = new ArrayList<>();

        if((results != null)) {
            IntStream.range(0, results.length())
                    .forEach(
                            r -> {
                                RestaurantDTO dto = new RestaurantDTO();
                                dto.setRestName(results.getJSONObject(r).getString("name"));
                                dto.setRating(results.getJSONObject(r).getInt("rating"));
                                dto.setUser_Ratings(results.getJSONObject(r).getInt("user_ratings_total"));
                                dto.setStatus(results.getJSONObject(r).getString("business_status"));
                                finalResultList.add(dto);
                            }
                    );

        }

        finalResultList.forEach(e -> System.out.println("RestaurantDTO--> "+ e));
        System.out.println("finalResultList"+ finalResultList);
        System.out.println("finalResultList.size "+ finalResultList.size());
        System.out.println(response);


        return finalResultList;
    }

    /**
     *
     * @param term
     * @param location
     * @return
     * @throws RestaurantServiceException
     */
    @Override
    public List<RestaurantDTO> getRestaurantFromYelp(String term, String location) throws RestaurantServiceException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + YELP_API_KEY);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(YELP_URL)
                .queryParam("term", term)
                .queryParam("location", location);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        final String body = response.getBody();

        //Convert String to Json Object
        JSONObject jsonObj = new JSONObject(body);
        JSONArray results = jsonObj.getJSONArray("businesses");

        List<RestaurantDTO> finalResultList = new ArrayList<>();

        if((results != null)) {
            IntStream.range(0, results.length())
                    .forEach(
                            r -> {
                                RestaurantDTO dto = new RestaurantDTO();
                                dto.setRestName(results.getJSONObject(r).getString("name"));
                                dto.setRating(results.getJSONObject(r).getInt("rating"));
                                dto.setUser_Ratings(results.getJSONObject(r).getInt("review_count"));
                                boolean isClosed = results.getJSONObject(r).getBoolean("is_closed");
                                if(isClosed) {
                                    dto.setStatus(CLOSED);
                                }else{
                                    dto.setStatus(OPEN);
                                }

                                JSONObject loc = results.getJSONObject(r).getJSONObject("location");
                                if(loc != null){
                                    String address  = loc.getString("address1");
                                    System.out.println("address"+ address);
                                }
                                System.out.println("loc"+ loc);
                                finalResultList.add(dto);
                            }
                    );

        }

        finalResultList.forEach(e -> System.out.println("RestaurantDTO--> "+ e));
        System.out.println("finalResultList"+ finalResultList);
        System.out.println("finalResultList.size "+ finalResultList.size());
        System.out.println(response);

        System.out.println("response"+response);

        return finalResultList;
    }


}
