package com.example.hotelmanagementbackend.repository;

import com.example.hotelmanagementbackend.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
}
