package com.TravelTourism.toursAndTravels.repositories;

import com.TravelTourism.toursAndTravels.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
