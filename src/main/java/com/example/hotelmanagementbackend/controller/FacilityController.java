package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.FacilityDTO;
import com.example.hotelmanagementbackend.dto.FacilityGalleryImageDTO;
import com.example.hotelmanagementbackend.service.FacilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/features")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAvailableFeatures(){
        List<FacilityDTO> list = facilityService.getAllAvailableFeatures();

        if(list.isEmpty()){
            ResponseEntity.ok(Map.of("message","there is no features"));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityDTO> getFeatureById(@PathVariable Integer id){
        FacilityDTO facilityDTO =  facilityService.getFeatureById(id);

        return ResponseEntity.ok(facilityDTO);
    }

    @GetMapping("/{id}/gallery")
    public  List<FacilityGalleryImageDTO> getGalleryByFacilityId(@PathVariable Integer id){
        return facilityService.getGalleryByFacilityId(id);
    }
}
