package com.TravelTourism.toursAndTravels.services.Strategy;

public interface MemberStrategy {
    default Double calculateFinalPriceWithPercentageDiscount(int discountPercentage, Double activityCost) {
        Double discount = activityCost - (discountPercentage / 100.0) * activityCost;
        return discount;
    }
    Double getDiscount(Double activityCost);
}
