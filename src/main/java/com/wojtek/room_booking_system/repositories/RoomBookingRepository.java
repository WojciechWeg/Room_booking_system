package com.wojtek.room_booking_system.repositories;

import com.wojtek.room_booking_system.dao.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity,Long> {


    @Query("select rB from RoomBookingEntity rB where  rB.roomName =?3 and  ?1  between rB.dateStart and rB.dateEnd and ?2  between rB.dateStart and rB.dateEnd" )
    List<RoomBookingEntity> selectBookingsWith(@Param("dateStartParam") LocalDateTime dateStartParam, @Param("dateEndParam") LocalDateTime dateEndParam, @Param("roomNameParam") String roomNameParam);

   // @Query("select rB from RoomBookingEntity rB where ?1  between rB.dateStart and rB.dateEnd and ?2  between rB.dateStart and rB.dateEnd")
    @Query("select rB from RoomBookingEntity rB")
    List<RoomBookingEntity> getAllBookingsFromTo(@Param("dateStartParam") LocalDateTime dateStartParam, @Param("dateEndParam") LocalDateTime dateEndParam);

    //  @Query("select rB from RoomBookingEntity rB")
    @Query("select rB from RoomBookingEntity rB where ?1  < rB.dateStart")
    List<RoomBookingEntity> getAllBookingsTo(@Param("dateEndParam") LocalDateTime dateEndParam);

    //@Query("select rB from RoomBookingEntity rB")
    @Query("select rB from RoomBookingEntity rB where ?1  > rB.dateEnd")
    List<RoomBookingEntity> getAllBookingsFrom(@Param("dateStartParam") LocalDateTime dateStartParam);
}
