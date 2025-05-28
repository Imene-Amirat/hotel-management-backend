package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.RoomCard;
import com.example.hotelmanagementbackend.dto.RoomGalleryImageDTO;
import com.example.hotelmanagementbackend.dto.RoomInfoDTO;
import com.example.hotelmanagementbackend.model.RoomType;
import com.example.hotelmanagementbackend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public List<RoomCard> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomType getRoomById(@PathVariable int id){
        return roomService.getRoomById(id);
    }

    @GetMapping("/{id}/gallery")
    public List<RoomGalleryImageDTO> getGalleryByRoomId(@PathVariable int id){
        return roomService.getGalleryByRoomId(id);
    }

    @GetMapping("/availability/check")
    public ResponseEntity<Map<String,Object>> checkAvailability(@RequestParam Integer roomTypeId,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut){
        boolean isAvailable = roomService.checkRoomAvailability(roomTypeId, checkIn, checkOut);

        return ResponseEntity.ok(Map.of("available", isAvailable));
    }

    @GetMapping("/availability/first")
    public ResponseEntity<RoomInfoDTO> getFirstAvailableRoom(@RequestParam Integer roomTypeId,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut){
        RoomInfoDTO room = roomService.getFirstAvailableRoom(roomTypeId, checkIn, checkOut);

        return ResponseEntity.ok(room);
    }
}
