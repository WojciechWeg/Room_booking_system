package com.wojtek.room_booking_system.services;


import com.wojtek.room_booking_system.dto.Room;
import com.wojtek.room_booking_system.dao.RoomEntity;

import com.wojtek.room_booking_system.repositories.RoomRepository;
import com.wojtek.room_booking_system.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void createNewRoom(Room room) {

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setName(room.getName());
        roomEntity.setNumberOfSeats(room.getNumberOfSeats());

        if(room.getLocation()!=null )
            roomEntity.setLocation(room.getLocation());
        if(room.getHasProjector()!=null )
            roomEntity.setHasProjector(room.getHasProjector());
        if(room.getPhoneNumber()!=null )
            roomEntity.setPhoneNumber(room.getPhoneNumber());

        roomRepository.save(roomEntity);
    }

    public void updateRoom(String roomName, Room room) {

        RoomEntity roomEntityUpdated = roomRepository.findById(roomName).orElseThrow(()->new ResourceNotFoundException("No such room."));

        if(room.getLocation()!=null && !room.getLocation().isEmpty() && !room.getLocation().equals(roomEntityUpdated.getLocation()))
            roomEntityUpdated.setLocation(room.getLocation());
        if(room.getNumberOfSeats()!=null && room.getNumberOfSeats()!=roomEntityUpdated.getNumberOfSeats())
            roomEntityUpdated.setNumberOfSeats(room.getNumberOfSeats());
        if(room.getHasProjector()!=null && !room.getHasProjector().equals(roomEntityUpdated.isHasProjector()))
            roomEntityUpdated.setHasProjector(room.getHasProjector());
        if(room.getPhoneNumber()!=null && !room.getPhoneNumber().equals(roomEntityUpdated.getPhoneNumber()))
            roomEntityUpdated.setPhoneNumber(room.getPhoneNumber());

        roomRepository.save(roomEntityUpdated);

    }

    public void delete(String roomName) {

        roomRepository.deleteById(roomName);

    }

    public List<Room> getAllRooms() {

        List<Room> list = new ArrayList<>();
        for (RoomEntity item : roomRepository.findAll()) {
            Room room = new Room();
            room.setName(item.getName());
            room.setLocation(item.getLocation());
            room.setNumberOfSeats(item.getNumberOfSeats());
            room.setHasProjector(item.isHasProjector());
            room.setPhoneNumber(item.getPhoneNumber());
            list.add(room);
        }
        return list;
    }
}
