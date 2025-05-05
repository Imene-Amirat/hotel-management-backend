package com.example.hotelmanagementbackend.repository;

import com.example.hotelmanagementbackend.model.RoomGalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomGalleryImageRepository extends JpaRepository<RoomGalleryImage, Integer> {
    List<RoomGalleryImage> findByRoomId(int id);
}
