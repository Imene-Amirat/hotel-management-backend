package com.example.hotelmanagementbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingCheckRequest {
    @NotNull(message = "Room type ID is required")
    private Integer roomTypeId;

    @NotNull(message = "checkIn date is required")
    private LocalDate checkIn;

    @NotNull(message = "checkOut date is required")
    private LocalDate checkOut;
}
