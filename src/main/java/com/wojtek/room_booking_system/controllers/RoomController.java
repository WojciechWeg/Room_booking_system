package com.wojtek.room_booking_system.controllers;

import com.wojtek.room_booking_system.model.Room;
import com.wojtek.room_booking_system.model.User;
import com.wojtek.room_booking_system.model.UserNoPassword;
import com.wojtek.room_booking_system.services.RoomService;
import com.wojtek.room_booking_system.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RoomController.BASE_URL)
public class RoomController {

    public static final String BASE_URL = "room";

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PutMapping()
    public void createNewUser(@RequestBody Room room) {
        roomService.createNewRoom(room);
    }

    @PostMapping("/{roomName}")
    public void update(@PathVariable String roomName, @RequestBody Room room){ roomService.updateRoom(roomName,room); }

    @DeleteMapping({"/{roomName}"})
    public void delete(@PathVariable String roomName){
        roomService.delete(roomName);
    }

    @GetMapping({"","/"})
    public List<Room> getAllUser(){
        return roomService.getAllRooms();
    }


}
