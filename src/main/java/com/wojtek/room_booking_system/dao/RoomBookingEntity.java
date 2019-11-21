package com.wojtek.room_booking_system.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="roomBookings")
@Setter
@Getter
public class RoomBookingEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    @Column(name="roomName")
    String roomName;

    @Column(name="userLogin")
    String userLogin;

    @Column(name="dateFrom")
    LocalDateTime dateFrom;

    @Column(name="dateTo")
    LocalDateTime dateTo;

}
