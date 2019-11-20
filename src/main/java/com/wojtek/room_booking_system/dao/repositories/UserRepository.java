package com.wojtek.room_booking_system.dao.repositories;

import com.wojtek.room_booking_system.dao.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<UserEntity,String> {

    


}
