package com.TravelTourism.toursAndTravels.dtos;

import com.TravelTourism.toursAndTravels.models.Passenger;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PassengerDetailsDto {
    private String name;
    private Integer passengerNumber;
    private Double balance;
    private List<PassengerActivityDto> enrolledActivities;
}
