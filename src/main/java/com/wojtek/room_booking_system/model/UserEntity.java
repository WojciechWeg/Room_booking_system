package com.wojtek.room_booking_system.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @Size(max=100)
    String login;

    @NotNull
    @Size(max=50)
    String name;

    @NotNull
    @Size(max=100)
    String surname;

    @NotNull
    @Size(min=6,max=100)
    String password;

}
