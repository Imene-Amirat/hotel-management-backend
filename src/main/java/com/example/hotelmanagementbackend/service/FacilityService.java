package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.FacilityDTO;
import com.example.hotelmanagementbackend.model.Facility;
import com.example.hotelmanagementbackend.repository.FacilityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public List<FacilityDTO> getAllAvailableFeatures(){
        List<Facility> list = facilityRepository.findByAvailableTrue();
        return mappedEntity(list);
    }

    private List<FacilityDTO> mappedEntity(List<Facility> list){
        FacilityDTO dto = new FacilityDTO();
        List<FacilityDTO> listDto = new ArrayList<>();

        for(Facility ele: list){
            dto.setId(ele.getId());
            dto.setName(ele.getName());
            dto.setShortDescription(ele.getShortDescription());
            dto.setImageUrl(ele.getImageUrl());

            listDto.add(dto);
        }
        return listDto;
    }











}
