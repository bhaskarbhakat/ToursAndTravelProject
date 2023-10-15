package com.TravelTourism.toursAndTravels.services.Strategy;

import org.springframework.stereotype.Service;

@Service
public class StandardMemberStrategy implements MemberStrategy{
    @Override
    public Double getDiscount(Double activityCost) {
        return calculateFinalPriceWithPercentageDiscount(0, activityCost);
    }
}
