package com.wojtek.room_booking_system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingNameSurname {

    @NotNull
    @NotEmpty
    @NotBlank
    String roomName;

    @NotNull
    @NotEmpty
    @NotBlank
    String userName;

    @NotNull
    @NotEmpty
    @NotBlank
    String userSurname;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateStart;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateEnd;


}
