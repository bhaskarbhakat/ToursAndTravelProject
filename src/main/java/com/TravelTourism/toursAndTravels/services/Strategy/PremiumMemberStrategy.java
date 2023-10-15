package com.TravelTourism.toursAndTravels.services.Strategy;

import org.springframework.stereotype.Service;

@Service
public class PremiumMemberStrategy implements MemberStrategy{
    @Override
    public Double getDiscount(Double activityCost) {
        return calculateFinalPriceWithPercentageDiscount(100, activityCost);
    }
}
