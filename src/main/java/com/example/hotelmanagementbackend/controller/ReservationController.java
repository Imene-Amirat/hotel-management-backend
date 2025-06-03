package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.CreateReservationRequest;
import com.example.hotelmanagementbackend.dto.ReservationPaymentInfoDTO;
import com.example.hotelmanagementbackend.dto.ReservationResponseDTO;
import com.example.hotelmanagementbackend.dto.UserReservationDTO;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createReservation(@Valid @RequestBody CreateReservationRequest res, HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session != null){
            User user = (User) session.getAttribute("user");
            if(user != null){
                int reservationId = reservationService.createReservation(res, user);
                ReservationResponseDTO response = new ReservationResponseDTO(reservationId, "Reservation created successfully!");

                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "You must be logged in.!"));
    }

    @GetMapping("/{id}")
    public ReservationPaymentInfoDTO getReservationById(@PathVariable Integer id){
        return reservationService.getReservationById(id);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllReservationsByUser(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session != null){
            User user = (User) session.getAttribute("user");
            if(user != null){
                List<UserReservationDTO> listReservations = reservationService.getAllReservationsByUser(user);

                return ResponseEntity.ok(listReservations);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "You must be logged in.!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteReservationByUser(@PathVariable Integer id, HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session != null){
            User user = (User) session.getAttribute("user");
            if(user != null){
                boolean deleted = reservationService.deleteReservationsByUser(id, user.getId());
                if(deleted){
                    return ResponseEntity.ok(Map.of("message", "Deleted successfully!"));
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "Reservation not found or does not belong to user."));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "You must be logged in.!"));
    }
}
