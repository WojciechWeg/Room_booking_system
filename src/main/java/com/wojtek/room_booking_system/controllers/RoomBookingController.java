package com.wojtek.room_booking_system.controllers;

import com.wojtek.room_booking_system.dao.RoomBookingEntity;
import com.wojtek.room_booking_system.dto.RoomBooking;
import com.wojtek.room_booking_system.dto.RoomBookingNameSurname;
import com.wojtek.room_booking_system.services.RoomBookingService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

    @GetMapping()
    public List<RoomBookingNameSurname> getBookingScheduleForAllRooms(@RequestParam String dateStart, @RequestParam String dateEnd){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(dateStart.equals(""))
            return roomBookingService.getBookingScheduleForAllRooms(null, LocalDateTime.parse(dateEnd,dateTimeFormatter));
        if(dateEnd.equals(""))
            return roomBookingService.getBookingScheduleForAllRooms(LocalDateTime.parse(dateStart,dateTimeFormatter), null);

        return roomBookingService.getBookingScheduleForAllRooms(LocalDateTime.parse(dateStart,dateTimeFormatter), LocalDateTime.parse(dateEnd,dateTimeFormatter));

    }



}
