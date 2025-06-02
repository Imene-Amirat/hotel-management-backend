package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.CreateReservationRequest;
import com.example.hotelmanagementbackend.dto.ReservationPaymentInfoDTO;
import com.example.hotelmanagementbackend.dto.UserReservationDTO;
import com.example.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.example.hotelmanagementbackend.model.Reservation;
import com.example.hotelmanagementbackend.model.ReservationStatus;
import com.example.hotelmanagementbackend.model.Room;
import com.example.hotelmanagementbackend.model.User;
import com.example.hotelmanagementbackend.repository.ReservationRepository;
import com.example.hotelmanagementbackend.repository.RoomRepository;
import com.example.hotelmanagementbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository1, UserRepository userRepository) {
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

    public List<UserReservationDTO> getAllReservationsByUser(User user){
        List<Reservation> reservations = reservationRepository.findByUserId(user.getId());

        if (reservations.isEmpty())
            return null;
        else {
            return mapToUserReservation(reservations);
        }
    }

    private List<UserReservationDTO> mapToUserReservation(List<Reservation> reservations){
        List<UserReservationDTO> listDto = new ArrayList<>();

        for(Reservation ele : reservations){
            UserReservationDTO dto = new UserReservationDTO();
            dto.setGuestFirstName(ele.getGuestFirstName());
            dto.setGuestLastName(ele.getGuestLastName());
            dto.setStatus(ele.getStatus());
            dto.setCheckIn(ele.getCheckIn());
            dto.setCheckOut(ele.getCheckOut());
            dto.setTotalPrice(ele.getTotalPrice());
            dto.setRoomNumber(ele.getRoom().getRoomNumber());
            dto.setNbNights((int) (ele.getTotalPrice() / ele.getRoom().getRoomType().getPricePerNight()));
            dto.setRoomType(ele.getRoom().getRoomType().getName());

            listDto.add(dto);
        }
        return listDto;
    }
}
