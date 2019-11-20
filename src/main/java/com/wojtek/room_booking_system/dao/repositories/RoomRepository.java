package com.wojtek.room_booking_system.dao.repositories;

import com.wojtek.room_booking_system.dao.model.RoomEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<RoomEntity,String> {
}
