package com.example.hotelmanagementbackend.repository;

import com.example.hotelmanagementbackend.model.Reservation;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
