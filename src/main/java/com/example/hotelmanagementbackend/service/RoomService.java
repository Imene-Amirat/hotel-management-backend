package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.RoomCard;
import com.example.hotelmanagementbackend.dto.RoomGalleryImageDTO;
import com.example.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.example.hotelmanagementbackend.model.Room;
import com.example.hotelmanagementbackend.model.RoomGalleryImage;
import com.example.hotelmanagementbackend.model.RoomType;
import com.example.hotelmanagementbackend.repository.RoomGalleryImageRepository;
import com.example.hotelmanagementbackend.repository.RoomRepository;
import com.example.hotelmanagementbackend.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomGalleryImageRepository roomGalleryImageRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, RoomGalleryImageRepository roomGalleryImageRepository, RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.roomRepository = roomRepository;
        this.roomGalleryImageRepository = roomGalleryImageRepository;
    }

    public List<RoomCard> getAllRooms() {
        List<RoomType> roomsType =  roomTypeRepository.findAll();

        List<RoomCard> roomCards = new ArrayList<>();
        for(RoomType roomType : roomsType){
            roomCards.add(mapToRoomCard(roomType));
        }
        return roomCards;
    }

    private RoomCard mapToRoomCard(RoomType roomType){
        RoomCard roomCard = new RoomCard();

        roomCard.setId(roomType.getId());
        roomCard.setName(roomType.getName());
        roomCard.setCapacityAdults(roomType.getCapacityAdults());
        roomCard.setCapacityChildren(roomType.getCapacityChildren());
        roomCard.setRoomSize(roomType.getRoomSize());
        roomCard.setBedType(roomType.getBedType());
        roomCard.setPricePerNight(roomType.getPricePerNight());
        roomCard.setImageUrl(roomType.getImageUrl());

        return roomCard;
    }
    
    public RoomType getRoomById(int id){
        return roomTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + id));
    }

    public List<RoomGalleryImageDTO> getGalleryByRoomId(int id){
        Optional<RoomType> roomType = roomTypeRepository.findById(id);

        if(roomType.isEmpty()){
            throw new ResourceNotFoundException("Room Type not found with ID: " + id);
        }
        List<RoomGalleryImage> roomImages = roomGalleryImageRepository.findByRoomTypeId(id);

        List<RoomGalleryImageDTO> roomImagesDTO = new ArrayList<>();
        for(RoomGalleryImage roomImage: roomImages){
            roomImagesDTO.add(mapToRoomGalleryDTO(roomImage));
        }
        return roomImagesDTO;
    }

    private RoomGalleryImageDTO mapToRoomGalleryDTO(RoomGalleryImage roomImage){
        RoomGalleryImageDTO roomImageDTO = new RoomGalleryImageDTO();
        roomImageDTO.setId(roomImage.getId());
        roomImageDTO.setImageUrl(roomImage.getImageUrl());

        return roomImageDTO;
    }
}
