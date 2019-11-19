package com.wojtek.room_booking_system.repositories;

import com.wojtek.room_booking_system.model.RoomEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<RoomEntity,String> {
}
