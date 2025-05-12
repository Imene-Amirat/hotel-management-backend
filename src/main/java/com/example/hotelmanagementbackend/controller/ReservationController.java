package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.BookingCheckRequest;
import com.example.hotelmanagementbackend.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/check-availability")
    public ResponseEntity<Map<String,Object>> checkAvailability(@Valid @RequestBody BookingCheckRequest bookingCheckRequest){
        boolean isAvailable = reservationService.checkAvailability(bookingCheckRequest.getRoomTypeId(), bookingCheckRequest.getCheckIn(), bookingCheckRequest.getCheckOut());

        return ResponseEntity.ok(Map.of("available", isAvailable));
    }
}
