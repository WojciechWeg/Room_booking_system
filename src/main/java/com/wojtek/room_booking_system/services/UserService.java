package com.wojtek.room_booking_system.services;

import com.wojtek.room_booking_system.model.User;
import com.wojtek.room_booking_system.model.UserEntity;
import com.wojtek.room_booking_system.model.UserNoPassword;
import com.wojtek.room_booking_system.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNewUser(User user) {
        UserEntity userEntity= new UserEntity();
        userEntity.setLogin(user.getLogin());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setPassword(user.getPassword());

        userRepository.save(userEntity);
    }

    public void updateUser(String userLogin, User user) {

        UserEntity userEntityUpdated = userRepository.findById(userLogin).orElseThrow(() ->  new RuntimeException("not found"));

        if(user.getName()!=null && !user.getName().isEmpty() && !user.getName().equals(userEntityUpdated.getName()) )
            userEntityUpdated.setName(user.getName());
        if(user.getPassword()!=null && !user.getPassword().isEmpty() && !user.getPassword().equals(userEntityUpdated.getPassword())  )
            userEntityUpdated.setPassword(user.getPassword());
        if(user.getSurname()!=null && !user.getSurname().isEmpty() && !user.getSurname().equals(userEntityUpdated.getSurname()) )
            userEntityUpdated.setSurname(user.getSurname());

        userRepository.save(userEntityUpdated);

    }

    public void delete(String userLogin){
        userRepository.deleteById(userLogin);
    }

    public List<UserNoPassword> getAllUsers() {
        userRepository.findAll();

        List<UserNoPassword> list = new ArrayList<>();
        for (UserEntity item : userRepository.findAll()) {
            UserNoPassword userNoPassword = new UserNoPassword();
            userNoPassword.setLogin(item.getLogin());
            userNoPassword.setName(item.getName());
            userNoPassword.setSurname(item.getSurname());
            list.add(userNoPassword);
        }
        return list;
    }
}
