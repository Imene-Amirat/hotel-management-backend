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
}
