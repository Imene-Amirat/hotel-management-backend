package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.RoomTypeSelectorDTO;
import com.example.hotelmanagementbackend.model.RoomType;
import com.example.hotelmanagementbackend.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomTypeSelectorDTO> getAllRoomTypes(){
        List<RoomType> roomTypes =  roomTypeRepository.findAll();

        List<RoomTypeSelectorDTO> listDto = new ArrayList<>();
        for (RoomType roomType: roomTypes){
            RoomTypeSelectorDTO dto = new RoomTypeSelectorDTO(roomType.getId(),roomType.getName());
            listDto.add(dto);
        }

        return listDto;
    }
}
