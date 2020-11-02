package com.example.skip.RestaurantSuggestionService.dto;

/**
 * @author Punit Dhakan
 * Restaurant DTO
 */
public class RestaurantDTO {

    private String restName;
    private String status;
    private int rating;
    private int user_Ratings;

    public RestaurantDTO(){ }

    public RestaurantDTO(String restName, String status, int rating, int user_Ratings) {
        this.restName = restName;
        this.status = status;
        this.rating = rating;
        this.user_Ratings = user_Ratings;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUser_Ratings() {
        return user_Ratings;
    }

    public void setUser_Ratings(int user_Ratings) {
        this.user_Ratings = user_Ratings;
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "restName='" + restName + '\'' +
                ", status='" + status + '\'' +
                ", rating=" + rating +
                ", user_Ratings=" + user_Ratings +
                '}';
    }
}