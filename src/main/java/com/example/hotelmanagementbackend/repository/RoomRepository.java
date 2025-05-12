package com.example.hotelmanagementbackend.repository;

import com.example.hotelmanagementbackend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.roomType.id = :roomTypeId AND r.isActive = true " +
            "AND NOT EXISTS (SELECT res FROM Reservation res WHERE res.room = r " +
            "AND res.checkIn < :checkOut AND res.checkOut > :checkIn)")
    List<Room> findAvailabilityRoomsByTypeAndDates(@Param("roomTypeId") int roomTypeId,
                                                   @Param("checkIn") LocalDate checkIn,
                                                   @Param("checkOut") LocalDate checkOut);
}
