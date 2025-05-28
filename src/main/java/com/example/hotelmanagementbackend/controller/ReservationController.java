package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.CreateReservationRequest;
import com.example.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/")
    public ResponseEntity<Map<String,String>> createReservation(@Valid @RequestBody CreateReservationRequest res, HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session != null){
            User user = (User) session.getAttribute("user");
            if(user != null){
                reservationService.createReservation(res, user);
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Reservation created successfully!"));
    }
}
