package com.TravelTourism.toursAndTravels;

import com.TravelTourism.toursAndTravels.models.PassengerType;
import com.TravelTourism.toursAndTravels.services.Strategy.GoldMemberStrategy;
import com.TravelTourism.toursAndTravels.services.Strategy.MemberStrategy;
import com.TravelTourism.toursAndTravels.services.Strategy.PremiumMemberStrategy;
import com.TravelTourism.toursAndTravels.services.Strategy.StandardMemberStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerTypeFactory {
    @Autowired
    private GoldMemberStrategy goldMemberStrategy;

    @Autowired
    private StandardMemberStrategy standardMemberStrategy;

    @Autowired
    private PremiumMemberStrategy premiumMemberStrategy;

    public MemberStrategy getMemberTypeStrategy(String passengerType) {
        if (passengerType.equals(PassengerType.GOLD)) {
            return goldMemberStrategy;
        }
        else if (passengerType.equals(PassengerType.STANDARD)) {
            return standardMemberStrategy;
        }
        else if (passengerType.equals(PassengerType.PREMIUM)) {
            return premiumMemberStrategy;
        }
        return null;
    }

}
