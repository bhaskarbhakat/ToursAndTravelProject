package com.TravelTourism.toursAndTravels.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TravelPackageItineraryDto {
    private String travelPackageName;
    private List<DestinationItineraryDto> destinations;
}
