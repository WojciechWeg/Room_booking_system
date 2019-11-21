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

        if(roomBooking.getDateEnd().isBefore(LocalDateTime.now()) || roomBooking.getDateStart().isBefore(LocalDateTime.now()))
            throw new DateMisfilled("You cant book room in the past");

        if(roomBooking.getDateEnd().equals(roomBooking.getDateStart()))
            throw new DateMisfilled("End date is the same like start date.");

        if(roomBooking.getDateEnd().isBefore(roomBooking.getDateStart()))
            throw new DateMisfilled("End date can't be before start date.");

        roomRepository.findById(roomBooking.getRoomName()).orElseThrow(() ->  new ResourceNotFoundException("No such room."));

        userRepository.findById(roomBooking.getUserLogin()).orElseThrow(() ->  new ResourceNotFoundException("No such user."));

        List<RoomBookingEntity> roomBookingEntityList =
                roomBookingRepository.getAllBookingsWithInDateFrameAndRoom(roomBooking.getDateStart(),roomBooking.getDateEnd(),roomBooking.getRoomName());

        if(!roomBookingEntityList.isEmpty())
            throw new RoomIsOccupiedException("Room is occupied at this time.");

        RoomBookingEntity roomBookingEntity = new RoomBookingEntity();
        roomBookingEntity.setRoomName(roomBooking.getRoomName());
        roomBookingEntity.setUserLogin(roomBooking.getUserLogin());
        roomBookingEntity.setDateStart(roomBooking.getDateStart());
        roomBookingEntity.setDateEnd(roomBooking.getDateEnd());

        roomBookingRepository.save(roomBookingEntity);
    }

    public List<RoomBookingNameSurname> getBookingScheduleForAllRooms(LocalDateTime dateStart, LocalDateTime dateEnd) {

        List<RoomBookingEntity> roomBookingEntityList = new LinkedList<>();

        if(dateStart!=null && dateEnd!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsWithIn(dateStart,dateEnd);

        if(dateStart==null && dateEnd!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInPast(dateEnd);

        if (dateStart!=null && dateEnd==null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInFuture(dateStart);

        if(dateStart==null && dateEnd==null)
            roomBookingEntityList = roomBookingRepository.findAll();


        return mapBookings(roomBookingEntityList);

    }

    public List<RoomBookingNameSurname> getBookingScheduleForGivenRoom(LocalDateTime dateStart, LocalDateTime dateEnd, String roomName) {

        roomRepository.findById(roomName).orElseThrow(()-> new ResourceNotFoundException("No such room"));


        List<RoomBookingEntity> roomBookingEntityList = new LinkedList<>();

        if(dateStart!=null && dateEnd!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsWithInDateFrameAndRoom(dateStart,dateEnd,roomName);

        if(dateStart==null && dateEnd!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInPastAndRoom(dateEnd,roomName);

        if (dateStart!=null && dateEnd==null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInFutureAndRoom(dateStart,roomName);

        if(dateStart==null && dateEnd==null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInRoom(roomName);


        return mapBookings(roomBookingEntityList);


    }

    private List<RoomBookingNameSurname> mapBookings(List<RoomBookingEntity> roomBookingEntityList) {
        List<RoomBookingNameSurname> roomBookingNameSurnameList = new LinkedList<>();
        for (RoomBookingEntity item : roomBookingEntityList) {

            RoomBookingNameSurname roomBookingNameSurname = new RoomBookingNameSurname();

            UserEntity userEntity = userRepository.findById(item.getUserLogin()).orElseThrow(() -> new ResourceNotFoundException("There is no such a user."));

            roomBookingNameSurname.setUserName(userEntity.getName());
            roomBookingNameSurname.setUserSurname(userEntity.getSurname());
            roomBookingNameSurname.setDateStart(item.getDateStart());
            roomBookingNameSurname.setDateEnd(item.getDateEnd());
            roomBookingNameSurname.setRoomName(item.getRoomName());

            roomBookingNameSurnameList.add(roomBookingNameSurname);

        }

        return roomBookingNameSurnameList;
    }
}
