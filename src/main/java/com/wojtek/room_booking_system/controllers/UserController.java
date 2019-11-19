package com.wojtek.room_booking_system.controllers;

import com.wojtek.room_booking_system.model.User;
import com.wojtek.room_booking_system.model.UserNoPassword;
import com.wojtek.room_booking_system.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "user";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping()
    public void createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
    }

    @PostMapping("/{userLogin}")
    public void update(@PathVariable String userLogin, @RequestBody User user){ userService.updateUser(userLogin,user); }

    @DeleteMapping({"/{userLogin}"})
    public void delete(@PathVariable String userLogin){
        userService.delete(userLogin);
    }

    @GetMapping({"","/"})
    public List<UserNoPassword> getAllUser(){
       return userService.getAllUsers();
    }


}
