package com.wojtek.room_booking_system.model;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserNoPassword {

    String login;

    String name;

    String surname;

}
