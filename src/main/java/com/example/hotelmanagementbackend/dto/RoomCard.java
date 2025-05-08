package com.example.hotelmanagementbackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomCard {
    private int id;
    private String name;
    private int capacityAdults;
    private int capacityChildren;
    private double pricePerNight;
    private double roomSize;
    private String bedType;
    private String imageUrl;
}
