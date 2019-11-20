package com.wojtek.room_booking_system.dao.model;

import lombok.*;

import javax.validation.constraints.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=50)
    String name;

    @Size(max=256)
    String location;

    @NotNull
    @Min(1)
    @Max(100)
    Integer numberOfSeats;

    Boolean hasProjector;

    @Size(max=100)
    String phoneNumber;

}
