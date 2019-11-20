package com.wojtek.room_booking_system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoomIsOccupiedException extends RuntimeException {

    public RoomIsOccupiedException(String s) {
        super(s);
    }
}
