package com.wojtek.room_booking_system.services;

import com.wojtek.room_booking_system.dao.model.RoomBooking;
import com.wojtek.room_booking_system.dao.model.RoomBookingEntity;
import com.wojtek.room_booking_system.dao.repositories.RoomBookingRepository;
import com.wojtek.room_booking_system.dao.repositories.RoomRepository;
import com.wojtek.room_booking_system.dao.repositories.UserRepository;
import com.wojtek.room_booking_system.exceptions.DateMisfilled;
import com.wojtek.room_booking_system.exceptions.ResourceNotFoundException;
import com.wojtek.room_booking_system.exceptions.RoomIsOccupiedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomBookingService(RoomBookingRepository roomBookingRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.roomBookingRepository = roomBookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public void bookTheRoom(RoomBooking roomBooking) {

        if(roomBooking.getDateTo().equals(roomBooking.getDateFrom()))
            throw new DateMisfilled("End date is the same like start date.");

        if(roomBooking.getDateTo().before(roomBooking.getDateFrom()))
            throw new DateMisfilled("End date can't be before start date.");

        roomRepository.findById(roomBooking.getRoomName()).orElseThrow(() ->  new ResourceNotFoundException("No such room."));

        userRepository.findById(roomBooking.getUserLogin()).orElseThrow(() ->  new ResourceNotFoundException("No such user."));

        List<RoomBookingEntity> roomBookingEntityList =
                roomBookingRepository.selectBookingsWith(roomBooking.getDateFrom(),roomBooking.getDateTo(),roomBooking.getRoomName());

        if(!roomBookingEntityList.isEmpty())
            throw new RoomIsOccupiedException("Room is occupied at this time.");

        RoomBookingEntity roomBookingEntity = new RoomBookingEntity();
        roomBookingEntity.setRoomName(roomBooking.getRoomName());
        roomBookingEntity.setUserLogin(roomBooking.getUserLogin());
        roomBookingEntity.setDateFrom(roomBooking.getDateFrom());
        roomBookingEntity.setDateTo(roomBooking.getDateTo());

        roomBookingRepository.save(roomBookingEntity);
    }
}
