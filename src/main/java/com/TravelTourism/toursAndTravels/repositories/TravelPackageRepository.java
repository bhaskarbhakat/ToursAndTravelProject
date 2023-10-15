package com.TravelTourism.toursAndTravels.repositories;

import com.TravelTourism.toursAndTravels.models.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
    // You can define custom query methods or use the ones provided by JpaRepository
}