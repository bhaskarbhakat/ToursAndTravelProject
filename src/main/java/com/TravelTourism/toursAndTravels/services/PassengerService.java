package com.TravelTourism.toursAndTravels.services;

import com.TravelTourism.toursAndTravels.PassengerTypeFactory;
import com.TravelTourism.toursAndTravels.models.Activity;
import com.TravelTourism.toursAndTravels.models.Passenger;
import com.TravelTourism.toursAndTravels.repositories.PassengerRepository;
import com.TravelTourism.toursAndTravels.services.Strategy.MemberStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerTypeFactory passengerFactory;

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Passenger updateActivityByPassenger(Long passengerId, List<Activity> activityList) {
        // Retrieve the passenger from the database
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found with ID: " + passengerId));

        passenger.setActivityList(activityList);
        MemberStrategy memberStrategy = passengerFactory.getMemberTypeStrategy(passenger.getPassengerType().toString());
        for (Activity activity: activityList) {
            Double discountValue = memberStrategy.getDiscount(activity.getCost());
            passenger.setWalletBalance(passenger.getWalletBalance()-discountValue);
        }
        return passengerRepository.save(passenger);
    }
}