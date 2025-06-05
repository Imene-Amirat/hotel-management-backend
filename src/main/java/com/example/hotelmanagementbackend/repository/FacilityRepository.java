package com.example.hotelmanagementbackend.repository;

import com.example.hotelmanagementbackend.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    List<Facility> findByAvailableTrue();
}
