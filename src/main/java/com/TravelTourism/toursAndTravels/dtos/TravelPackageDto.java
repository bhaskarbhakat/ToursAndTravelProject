package com.TravelTourism.toursAndTravels.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TravelPackageDto {
    private String name;
    private Double cost;
    private Integer capacity;
    private Date startDate;
    private Integer enrolledPassengersCount;
    private List<DestinationDto> itineraryList;
    private List<PassengerDto> passengerList;
}
