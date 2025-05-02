package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.RoomCard;
import com.example.hotelmanagementbackend.model.Room;
import com.example.hotelmanagementbackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
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

    public void createRoom(Room room){
        roomRepository.save(room);
    }

    public void deleteRoom(Long id) {

    }
}
