package com.wojtek.room_booking_system.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=100)
    String login;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=50)
    String name;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=100)
    String surname;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=6,max=100)
    String password;

}
