package com.wojtek.room_booking_system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateMisfilled extends RuntimeException {

    public DateMisfilled(String s) {
        super(s);
    }
}
