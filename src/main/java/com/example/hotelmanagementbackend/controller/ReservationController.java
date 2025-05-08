package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.BookingCheckRequest;
import com.example.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.example.hotelmanagementbackend.model.RoomType;
import com.example.hotelmanagementbackend.repository.RoomTypeRepository;
import com.example.hotelmanagementbackend.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/check-availability")
    public void checkAvailability(@Valid @RequestBody BookingCheckRequest bookingCheckRequest){
        reservationService.checkAvailability(bookingCheckRequest.getRoomTypeId(), bookingCheckRequest.getCheckIn(), bookingCheckRequest.getCheckOut());
    }
}
