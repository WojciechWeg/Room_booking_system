package com.wojtek.room_booking_system.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="rooms")
@Data
@Setter
@Getter
public class RoomEntity {

    @Id
    @Column(name="name")
    @Size(max=50)
    String name;

    @Column(name="location", columnDefinition = "varchar(255) default 'na'")
    @Size(max=256)
    String location;

    @NotNull
    @Column(name="numberOfSeats")
    @Min(1)
    @Max(100)
    int numberOfSeats;

    @Column(name="hasProjector",columnDefinition = "boolean default false")
    boolean hasProjector;

    @Column(name="phoneNumber")
    @Size(max=100)
    String phoneNumber;


}
