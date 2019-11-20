package com.wojtek.room_booking_system.dao.model;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    String login;

    String name;

    String surname;

    String password;

}
