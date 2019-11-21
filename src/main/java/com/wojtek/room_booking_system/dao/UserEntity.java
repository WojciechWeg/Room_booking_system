package com.wojtek.room_booking_system.dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
@Data
@Setter
@Getter
public class UserEntity {

    @Id
    @Column(name="login")
    String login;

    @Column(name="name")
    String name;

    @Column(name="surname")
    String surname;

    @Column(name="password")
    String password;

}
