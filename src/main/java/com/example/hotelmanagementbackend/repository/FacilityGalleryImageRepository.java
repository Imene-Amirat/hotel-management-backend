package com.example.hotelmanagementbackend.repository;

import com.example.hotelmanagementbackend.model.FacilityGalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityGalleryImageRepository extends JpaRepository<FacilityGalleryImage, Integer> {
    List<FacilityGalleryImage> findByFacilityId(Integer id);
}
