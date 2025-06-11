package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.RoomTypeSelectorDTO;
import com.example.hotelmanagementbackend.service.RoomTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/room-types")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping("")
    private List<RoomTypeSelectorDTO> getAllRoomTypes(){
        return roomTypeService.getAllRoomTypes();
    }
}
