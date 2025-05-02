package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.RoomCard;
import com.example.hotelmanagementbackend.model.Room;
import com.example.hotelmanagementbackend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public List<RoomCard> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public ResponseEntity<String> addRoom(@RequestBody Room room) {
        roomService.createRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body("Room created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully!");

    }
}
