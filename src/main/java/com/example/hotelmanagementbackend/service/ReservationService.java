package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.example.hotelmanagementbackend.model.Room;
import com.example.hotelmanagementbackend.model.RoomType;
import com.example.hotelmanagementbackend.repository.ReservationRepository;
import com.example.hotelmanagementbackend.repository.RoomRepository;
import com.example.hotelmanagementbackend.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomTypeRepository roomTypeRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.roomRepository = roomRepository;
    }

    public boolean checkAvailability(int roomTypeId, LocalDate checkIn, LocalDate checkOut) {
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("room type not found with this ID " + roomTypeId));

        List<Room> availableRooms = roomRepository.findAvailabilityRoomsByTypeAndDates(roomTypeId, checkIn, checkOut);

        return !availableRooms.isEmpty();
    }
}
