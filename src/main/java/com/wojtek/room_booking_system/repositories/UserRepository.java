package com.wojtek.room_booking_system.repositories;

import com.wojtek.room_booking_system.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<UserEntity,String> {

    


}
