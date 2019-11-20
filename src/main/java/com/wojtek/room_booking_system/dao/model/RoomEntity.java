package com.wojtek.room_booking_system.dao.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rooms")
@Data
@Setter
@Getter
public class RoomEntity {

    @Id
    @Column(name="name")
    String name;

    @Column(name="location", columnDefinition = "varchar(255) default 'na'")
    String location;

    @Column(name="numberOfSeats")
    int numberOfSeats;

    @Column(name="hasProjector",columnDefinition = "boolean default false")
    boolean hasProjector;

    @Column(name="phoneNumber")
    String phoneNumber;


}
