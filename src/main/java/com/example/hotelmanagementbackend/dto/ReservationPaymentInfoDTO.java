package com.example.hotelmanagementbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationPaymentInfoDTO {
    private String guestFirstName;
    private String guestLastName;
    private String guestPhone;
    private int roomNumber;
    private Date checkIn;
    private Date checkOut;
    private double totalPrice;
}
