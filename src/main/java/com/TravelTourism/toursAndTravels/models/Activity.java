package com.TravelTourism.toursAndTravels.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double cost;
    private Integer capacity;
    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;
    private Integer enrolledPassengersCount;
    @ManyToMany(mappedBy = "activityList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Passenger> passengers;
}
