package com.TravelTourism.toursAndTravels.services;

import com.TravelTourism.toursAndTravels.PassengerTypeFactory;
import com.TravelTourism.toursAndTravels.dtos.*;
import com.TravelTourism.toursAndTravels.exceptions.PassengerNotFoundException;
import com.TravelTourism.toursAndTravels.exceptions.TravelPackageNotFoundException;
import com.TravelTourism.toursAndTravels.models.Activity;
import com.TravelTourism.toursAndTravels.models.Destination;
import com.TravelTourism.toursAndTravels.models.Passenger;
import com.TravelTourism.toursAndTravels.models.TravelPackage;
import com.TravelTourism.toursAndTravels.repositories.ActivityRepository;
import com.TravelTourism.toursAndTravels.repositories.PassengerRepository;
import com.TravelTourism.toursAndTravels.repositories.TravelPackageRepository;
import com.TravelTourism.toursAndTravels.services.Strategy.MemberStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelAgencyService {
    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerTypeFactory passengerFactory;

    public TravelPackageItineraryDto getItineraryDetails(Long travelPackageId) {
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new TravelPackageNotFoundException("Travel Package not found with ID: " + travelPackageId));

        TravelPackageItineraryDto itineraryDto = new TravelPackageItineraryDto();
        itineraryDto.setTravelPackageName(travelPackage.getName());

        List<DestinationItineraryDto> destinationList = new ArrayList<>();
        for (Destination destination : travelPackage.getItineraryList()) {
            DestinationItineraryDto destinationItineraryDto = new DestinationItineraryDto();
            destinationItineraryDto.setDestinationName(destination.getName());

            List<ActivityDto> activityList = new ArrayList<>();
            for (Activity activity : destination.getActivityList()) {
                ActivityDto activityDto = new ActivityDto();
                activityDto.setName(activity.getName());
                activityDto.setCost(activity.getCost());
                activityDto.setCapacity(activity.getCapacity());
                activityDto.setDescription(activity.getDescription());
                activityList.add(activityDto);
            }
            destinationItineraryDto.setActivityList(activityList);
            destinationList.add(destinationItineraryDto);
        }

        itineraryDto.setDestinations(destinationList);
        return itineraryDto;
    }

    public TravelPackageDto getPassengerList(Long travelPackageId) {
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new TravelPackageNotFoundException("Travel Package not found with ID: " + travelPackageId));

        TravelPackageDto travelPackageDto = new TravelPackageDto();
        travelPackageDto.setName(travelPackage.getName());
        travelPackageDto.setCapacity(travelPackage.getCapacity());
        travelPackageDto.setEnrolledPassengersCount(travelPackage.getEnrolledPassengersCount());
        List<PassengerDto> passengerDtos = new ArrayList<>();
        List<Passenger> passengers = travelPackage.getPassengerList();

        for (Passenger passenger : passengers) {
            PassengerDto passengerDto = new PassengerDto();
            passengerDto.setName(passenger.getName());
            passengerDto.setPassengerNumber(passenger.getPassengerNumber());
            passengerDtos.add(passengerDto);
        }
        int enrolledPassengersCount = passengers.size();
        travelPackageDto.setPassengerList(passengerDtos);
        return travelPackageDto;
    }

    public PassengerDetailsDto getPassengerDetails(Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with ID: " + passengerId));

        PassengerDetailsDto passengerDetailsDto = new PassengerDetailsDto();
        passengerDetailsDto.setName(passenger.getName());
        passengerDetailsDto.setPassengerNumber(passenger.getPassengerNumber());
        passengerDetailsDto.setBalance(passenger.getWalletBalance());

        List<PassengerActivityDto> passengerActivityDtos = new ArrayList<>();
        List<Activity> activities = passenger.getActivityList();

        for (Activity activity : activities) {
            PassengerActivityDto passengerActivityDto = new PassengerActivityDto();
            passengerActivityDto.setActivityName(activity.getName());
            passengerActivityDto.setDestinationName(activity.getDestination().getName());
            MemberStrategy memberStrategy = passengerFactory.getMemberTypeStrategy(passenger.getPassengerType());
            passengerActivityDto.setPricePaidByUser(memberStrategy.getDiscount(activity.getCost()));
            passengerActivityDtos.add(passengerActivityDto);
        }

        passengerDetailsDto.setEnrolledActivities(passengerActivityDtos);

        return passengerDetailsDto;
    }

    public List<AvailableActivityDto> getAvailableActivities() {
        List<AvailableActivityDto> availableActivities = new ArrayList<>();
        List<Activity> allActivities = activityRepository.findAll();

        for (Activity activity : allActivities) {
            int availableSpaces = activity.getCapacity() - activity.getEnrolledPassengersCount();
            if (availableSpaces > 0) {
                AvailableActivityDto availableActivityDto = new AvailableActivityDto();
                availableActivityDto.setActivityName(activity.getName());
                availableActivityDto.setAvailableSpaces(availableSpaces);
                availableActivities.add(availableActivityDto);
            }
        }
        return availableActivities;
    }
}