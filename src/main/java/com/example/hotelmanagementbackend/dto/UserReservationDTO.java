package com.example.hotelmanagementbackend.dto;

import com.example.hotelmanagementbackend.model.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReservationDTO {

    private int id;
    private String guestFirstName;
    private String guestLastName;
    private int roomNumber;
    private int nbNights;
    private String roomType;
    private ReservationStatus status;
    private Date checkIn;
    private Date checkOut;
    private Double totalPrice;
}
