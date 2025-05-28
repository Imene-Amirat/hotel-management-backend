package com.example.hotelmanagementbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateReservationRequest {
    @NotNull(message = "Check-in date is required")
    private Date checkIn;

    @NotNull(message = "Check-out date is required")
    private Date checkOut;

    @NotNull(message = "room Id is required")
    private int roomId;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Total Price is required")
    private Double totalPrice;

    @NotBlank(message = "Status is required")
    private String status;
}
