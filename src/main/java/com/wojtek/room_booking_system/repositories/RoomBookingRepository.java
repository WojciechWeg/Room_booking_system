package com.wojtek.room_booking_system.repositories;

import com.wojtek.room_booking_system.dao.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity,Long> {

    @Query("select rB from RoomBookingEntity rB where ?1  between rB.dateStart and rB.dateEnd and ?2  between rB.dateStart and rB.dateEnd")
    List<RoomBookingEntity> getAllBookingsWithIn(@Param("dateStartParam") LocalDateTime dateStartParam, @Param("dateEndParam") LocalDateTime dateEndParam);

    @Query("select rB from RoomBookingEntity rB where ?1  < rB.dateStart")
    List<RoomBookingEntity> getAllBookingsInPast(@Param("dateEndParam") LocalDateTime dateEndParam);

    @Query("select rB from RoomBookingEntity rB where ?1  > rB.dateStart")
    List<RoomBookingEntity> getAllBookingsInFuture(@Param("dateStartParam") LocalDateTime dateStartParam);

    @Query("select rB from RoomBookingEntity rB where ?1  between rB.dateStart and rB.dateEnd and ?2  between rB.dateStart and rB.dateEnd and rB.roomName = ?3")
    List<RoomBookingEntity> getAllBookingsWithInDateFrameAndRoom(@Param("dateStartParam") LocalDateTime dateStartParam, @Param("dateEndParam") LocalDateTime dateEndParam, @Param("roomName") String roomName);

    @Query("select rB from RoomBookingEntity rB where ?1  < rB.dateStart and rB.roomName = ?2 ")
    List<RoomBookingEntity> getAllBookingsInPastAndRoom(LocalDateTime dateEnd, String roomName);

    @Query("select rB from RoomBookingEntity rB where ?1  > rB.dateStart and rB.roomName = ?2")
    List<RoomBookingEntity> getAllBookingsInFutureAndRoom(LocalDateTime dateStart, String roomName);

    @Query("select rB from RoomBookingEntity rB where  rB.roomName = ?1")
    List<RoomBookingEntity> getAllBookingsInRoom(String roomName);

    @Query("select rB from RoomBookingEntity rB where ?1  between rB.dateStart and rB.dateEnd and ?2  between rB.dateStart and rB.dateEnd and rB.userLogin = ?3")
    List<RoomBookingEntity> getAllBookingsWithInDateFrameAndUser(@Param("dateStartParam") LocalDateTime dateStartParam, @Param("dateEndParam") LocalDateTime dateEndParam, @Param("roomName") String roomName);

    @Query("select rB from RoomBookingEntity rB where ?1  < rB.dateStart and rB.userLogin = ?2 ")
    List<RoomBookingEntity> getAllBookingsInPastAndUser(LocalDateTime dateEnd, String login);

    @Query("select rB from RoomBookingEntity rB where ?1  > rB.dateStart and rB.userLogin = ?2")
    List<RoomBookingEntity> getAllBookingsInFutureAndUser(LocalDateTime dateStart, String login);

    @Query("select rB from RoomBookingEntity rB where  rB.userLogin = ?1")
    List<RoomBookingEntity> getAllBookingsForUser(String login);

}
