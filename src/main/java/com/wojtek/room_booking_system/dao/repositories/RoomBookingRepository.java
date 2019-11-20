package com.wojtek.room_booking_system.dao.repositories;

import com.wojtek.room_booking_system.dao.model.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity,Long> {


    @Query("select rB from RoomBookingEntity rB where  rB.roomName =?3 and  ?1  between rB.dateFrom and rB.dateTo and ?2  between rB.dateFrom and rB.dateTo" )
    List<RoomBookingEntity> selectBookingsWith(@Param("dateFromParam") Date dateFromParam, @Param("dateToParam") Date dateToParam, @Param("roomNameParam") String roomNameParam);

}
