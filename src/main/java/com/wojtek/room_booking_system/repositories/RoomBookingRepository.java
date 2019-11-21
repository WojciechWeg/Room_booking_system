package com.wojtek.room_booking_system.repositories;

import com.wojtek.room_booking_system.dao.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity,Long> {


    @Query("select rB from RoomBookingEntity rB where  rB.roomName =?3 and  ?1  between rB.dateFrom and rB.dateTo and ?2  between rB.dateFrom and rB.dateTo" )
    List<RoomBookingEntity> selectBookingsWith(@Param("dateFromParam") LocalDateTime dateFromParam, @Param("dateToParam") LocalDateTime dateToParam, @Param("roomNameParam") String roomNameParam);

   // @Query("select rB from RoomBookingEntity rB where ?1  between rB.dateFrom and rB.dateTo and ?2  between rB.dateFrom and rB.dateTo")
    @Query("select rB from RoomBookingEntity rB")
    List<RoomBookingEntity> getAllBookingsFromTo(@Param("dateFromParam") LocalDateTime dateFromParam, @Param("dateToParam") LocalDateTime dateToParam);

   // @Query("select rB from RoomBookingEntity rB where ?1  < rB.dateFrom")
     @Query("select rB from RoomBookingEntity rB")
    List<RoomBookingEntity> getAllBookingsTo(@Param("dateToParam") LocalDateTime dateToParam);

    //@Query("select rB from RoomBookingEntity rB where ?1  > rB.dateTo")
    @Query("select rB from RoomBookingEntity rB")
    List<RoomBookingEntity> getAllBookingsFrom(@Param("dateFromParam") LocalDateTime dateFromParam);
}
