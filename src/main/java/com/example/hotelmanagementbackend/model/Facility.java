package com.example.hotelmanagementbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shortDescription;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String fullDescription;

    @Column(nullable = false)
    private String openingHours;

    @Column(nullable = false)
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(nullable = false)
    private String imageUrl;
}
