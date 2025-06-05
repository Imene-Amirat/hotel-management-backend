package com.example.hotelmanagementbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDTO {
    private int id;
    private String name;
    private String shortDescription;
    private String imageUrl;

}
