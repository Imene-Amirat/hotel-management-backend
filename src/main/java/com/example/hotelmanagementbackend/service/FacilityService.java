package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.FacilityDTO;
import com.example.hotelmanagementbackend.dto.FacilityGalleryImageDTO;
import com.example.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.example.hotelmanagementbackend.model.Facility;
import com.example.hotelmanagementbackend.model.FacilityGalleryImage;
import com.example.hotelmanagementbackend.repository.FacilityGalleryImageRepository;
import com.example.hotelmanagementbackend.repository.FacilityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final FacilityGalleryImageRepository facilityGalleryImageRepository;

    public FacilityService(FacilityRepository facilityRepository, FacilityGalleryImageRepository facilityGalleryImageRepository) {
        this.facilityRepository = facilityRepository;
        this.facilityGalleryImageRepository = facilityGalleryImageRepository;
    }

    public List<FacilityDTO> getAllAvailableFeatures(){
        List<Facility> list = facilityRepository.findByAvailableTrue();
        return mappedListOfEntity(list);
    }

    private List<FacilityDTO> mappedListOfEntity(List<Facility> list){
        List<FacilityDTO> listDto = new ArrayList<>();

        for(Facility ele: list){
            FacilityDTO dto = new FacilityDTO();
            dto.setId(ele.getId());
            dto.setName(ele.getName());
            dto.setShortDescription(ele.getShortDescription());
            dto.setImageUrl(ele.getImageUrl());

            listDto.add(dto);
        }
        return listDto;
    }

    public FacilityDTO getFeatureById(Integer id){
        Optional<Facility> facility = facilityRepository.findById(id);

        if(facility.isEmpty())
            throw new ResourceNotFoundException("Facility not found with ID: " + id);
        else
            return mappedEntity(facility.get());
    }

    private FacilityDTO mappedEntity(Facility facility){
        FacilityDTO dto = new FacilityDTO();
        dto.setId(facility.getId());
        dto.setName(facility.getName());
        dto.setShortDescription(facility.getShortDescription());
        dto.setFullDescription(facility.getFullDescription());
        dto.setOpeningHours(facility.getOpeningHours());
        dto.setImageUrl(facility.getImageUrl());

        return dto;
    }

    public List<FacilityGalleryImageDTO> getGalleryByFacilityId(Integer id){
        Optional<Facility> facility = facilityRepository.findById(id);

        if(facility.isEmpty()){
            throw new ResourceNotFoundException("facility not found with ID: " + id);
        }
        List<FacilityGalleryImage> facilityImages = facilityGalleryImageRepository.findByFacilityId(id);

        List<FacilityGalleryImageDTO> facilityImageDTO = new ArrayList<>();
        for(FacilityGalleryImage facilityImage: facilityImages){
            facilityImageDTO.add(mapToFacilityGalleryDTO(facilityImage));
        }
        return facilityImageDTO;
    }

    private FacilityGalleryImageDTO mapToFacilityGalleryDTO(FacilityGalleryImage facilityImage){
        FacilityGalleryImageDTO facilityImageDTO = new FacilityGalleryImageDTO();
        facilityImageDTO.setId(facilityImage.getId());
        facilityImageDTO.setImageUrl(facilityImage.getImageUrl());

        return facilityImageDTO;
    }
}
