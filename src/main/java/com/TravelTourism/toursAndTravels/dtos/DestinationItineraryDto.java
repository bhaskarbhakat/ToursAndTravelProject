package com.TravelTourism.toursAndTravels.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DestinationItineraryDto {
    private String destinationName;
    private List<ActivityDto> activityList;
}
