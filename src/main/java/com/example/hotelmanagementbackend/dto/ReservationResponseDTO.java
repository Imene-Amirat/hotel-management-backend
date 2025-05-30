package com.example.hotelmanagementbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReservationResponseDTO {
    private int reservationId;
    private String message;
}
