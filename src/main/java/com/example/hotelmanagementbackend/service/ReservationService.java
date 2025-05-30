package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.CreateReservationRequest;
import com.example.hotelmanagementbackend.dto.ReservationPaymentInfoDTO;
import com.example.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.example.hotelmanagementbackend.model.Reservation;
import com.example.hotelmanagementbackend.model.ReservationStatus;
import com.example.hotelmanagementbackend.model.Room;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.repository.ReservationRepository;
import com.example.hotelmanagementbackend.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository1) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository1;
    }

    public int createReservation(CreateReservationRequest res, User user){
        Reservation reservation = reservationRepository.save(mappedEntity(res, user));
        return reservation.getId();
    }

    private Reservation mappedEntity(CreateReservationRequest res, User user){
        Reservation dto = new Reservation();
        Room room = roomRepository.findById(res.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        dto.setUser(user);
        dto.setRoom(room);
        dto.setCheckIn(res.getCheckIn());
        dto.setCheckOut(res.getCheckOut());
        dto.setGuestFirstName(res.getFirstName());
        dto.setGuestLastName(res.getLastName());
        dto.setGuestCity(res.getCity());
        dto.setGuestPhone(res.getPhone());
        dto.setTotalPrice(res.getTotalPrice());
        dto.setStatus(ReservationStatus.PENDING);

        return dto;
    }

    public ReservationPaymentInfoDTO getReservationById(Integer id){
        Reservation resFound = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));

        return mapToDTO(resFound);
    }

    private ReservationPaymentInfoDTO mapToDTO(Reservation res){
        ReservationPaymentInfoDTO dto = new ReservationPaymentInfoDTO();
        dto.setGuestFirstName(res.getGuestFirstName());
        dto.setGuestLastName(res.getGuestLastName());
        dto.setCheckIn(res.getCheckIn());
        dto.setCheckOut(res.getCheckOut());
        dto.setGuestPhone(res.getGuestPhone());
        dto.setTotalPrice(res.getTotalPrice());
        dto.setRoomNumber(res.getRoom().getRoomNumber());

        return dto;
    }
}
