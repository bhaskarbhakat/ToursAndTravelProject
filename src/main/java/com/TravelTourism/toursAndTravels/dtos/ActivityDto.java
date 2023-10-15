package com.TravelTourism.toursAndTravels.dtos;

import lombok.Data;
@Data
public class ActivityDto {
    private String name;
    private String description;
    private Double cost;
    private Integer capacity;
    private Integer enrolledPassengersCount;
}
