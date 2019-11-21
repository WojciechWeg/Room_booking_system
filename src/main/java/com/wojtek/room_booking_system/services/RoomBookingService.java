package com.wojtek.room_booking_system.services;

import com.wojtek.room_booking_system.dao.UserEntity;
import com.wojtek.room_booking_system.dto.RoomBooking;
import com.wojtek.room_booking_system.dao.RoomBookingEntity;
import com.wojtek.room_booking_system.dto.RoomBookingNameSurname;
import com.wojtek.room_booking_system.repositories.RoomBookingRepository;
import com.wojtek.room_booking_system.repositories.RoomRepository;
import com.wojtek.room_booking_system.repositories.UserRepository;
import com.wojtek.room_booking_system.exceptions.DateMisfilled;
import com.wojtek.room_booking_system.exceptions.ResourceNotFoundException;
import com.wojtek.room_booking_system.exceptions.RoomIsOccupiedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
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

        if(roomBooking.getDateTo().isBefore(roomBooking.getDateFrom()))
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

    public List<RoomBookingEntity> getBookingScheduleForAllRooms(LocalDateTime dateFrom, LocalDateTime dateTo) {

        List<RoomBookingEntity> roomBookingEntityList = new LinkedList<>();

        if(dateFrom!=null && dateTo!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsFromTo(dateFrom,dateTo);

        if(dateFrom==null && dateTo!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsTo(dateTo);

        if (dateFrom!=null && dateTo==null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsFrom(dateFrom);


        List<RoomBookingNameSurname> roomBookingNameSurnameList = new LinkedList<>();
        for ( RoomBookingEntity item: roomBookingEntityList) {

                RoomBookingNameSurname roomBookingNameSurname = new RoomBookingNameSurname();

                UserEntity userEntity = userRepository.findById(item.getUserLogin()).orElseThrow( ()->new ResourceNotFoundException("There is no such a user."));

                roomBookingNameSurname.setUserName(userEntity.getName());
                roomBookingNameSurname.setUserSurname(userEntity.getSurname());
                roomBookingNameSurname.setDateFrom(item.getDateFrom());
                roomBookingNameSurname.setDateTo(item.getDateTo());
                roomBookingNameSurname.setRoomName(item.getRoomName());

                roomBookingNameSurnameList.add(roomBookingNameSurname);

        }

        return roomBookingEntityList;

    }
}
