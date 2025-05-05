package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.RoomCard;
import com.example.hotelmanagementbackend.dto.RoomGalleryImageDTO;
import com.example.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.example.hotelmanagementbackend.model.Room;
import com.example.hotelmanagementbackend.model.RoomGalleryImage;
import com.example.hotelmanagementbackend.repository.RoomGalleryImageRepository;
import com.example.hotelmanagementbackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomGalleryImageRepository roomGalleryImageRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, RoomGalleryImageRepository roomGalleryImageRepository) {
        this.roomRepository = roomRepository;
        this.roomGalleryImageRepository = roomGalleryImageRepository;
    }

    public List<RoomCard> getAllRooms() {
        List<Room> rooms =  roomRepository.findAll();

        List<RoomCard> roomCards = new ArrayList<>();
        for(Room room: rooms){
            roomCards.add(mapToRoomCard(room));
        }
        return roomCards;
    }

    private RoomCard mapToRoomCard(Room room){
        RoomCard roomCard = new RoomCard();

        roomCard.setId(room.getId());
        roomCard.setType(room.getType());
        roomCard.setCapacityAdults(room.getCapacityAdults());
        roomCard.setCapacityChildren(room.getCapacityChildren());
        roomCard.setPricePerNight(room.getPricePerNight());
        roomCard.setImageUrl(room.getImageUrl());

        return roomCard;
    }
    
    public Room getRoomById(int id){
        return roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + id));
    }

    public List<RoomGalleryImageDTO> getGalleryByRoomId(int id){
        Optional<Room> room = roomRepository.findById(id);

        if(room.isEmpty()){
            throw new ResourceNotFoundException("Room not found with ID: " + id);
        }
        List<RoomGalleryImage> roomImages = roomGalleryImageRepository.findByRoomId(id);

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

    public void createRoom(Room room){
        roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
    }
}
