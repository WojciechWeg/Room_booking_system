package com.wojtek.room_booking_system.model;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    String name;

    String location;

    Integer numberOfSeats;

    Boolean hasProjector;

    String phoneNumber;

}
