package com.example.hotelmanagementbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int capacityAdults;

    @Column(nullable = false)
    private int capacityChildren;

    @Column(nullable = false)
    private double pricePerNight;

    @Column(nullable = false)
    private String imageUrl;

    // NEW FEATURES
    @Column(nullable = false)
    private boolean hasWifi;

    @Column(nullable = false)
    private boolean hasAirConditioning;

    @Column(nullable = false)
    private boolean hasTV;

    @Column(nullable = false)
    private boolean hasBreakfastIncluded;

    @Column(nullable = false)
    private double roomSize;

    @Column(nullable = false)
    private String bedType; 

    @Column(nullable = false)
    private boolean hasBalcony;

    @Column(nullable = false)
    private boolean hasTerrace;

    @Column(nullable = false)
    private boolean hasKitchen;
}
