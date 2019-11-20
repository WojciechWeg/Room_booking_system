package com.wojtek.room_booking_system.controllers;

import com.wojtek.room_booking_system.dao.model.Room;
import com.wojtek.room_booking_system.dao.model.RoomBooking;
import com.wojtek.room_booking_system.services.RoomBookingService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(RoomBookingController.BASE_URL)
public class RoomBookingController {

    public static final String BASE_URL = "bookRoom";

    private final RoomBookingService roomBookingService;

    public RoomBookingController(RoomBookingService roomBookingService) {
        this.roomBookingService = roomBookingService;
    }

    @PutMapping()
    public void newBooking(@Valid @RequestBody RoomBooking roomBooking) {
        roomBookingService.bookTheRoom(roomBooking);
    }

}
