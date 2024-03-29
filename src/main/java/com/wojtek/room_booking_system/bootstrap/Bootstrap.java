package com.wojtek.room_booking_system.bootstrap;

import com.wojtek.room_booking_system.dao.RoomEntity;
import com.wojtek.room_booking_system.dao.UserEntity;
import com.wojtek.room_booking_system.repositories.RoomRepository;
import com.wojtek.room_booking_system.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public Bootstrap(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        UserEntity userEntity = new UserEntity();
        userEntity.setName("John");
        userEntity.setSurname("Smith");
        userEntity.setLogin("jsmith");
        userEntity.setPassword("qwerty");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setName("Jane");
        userEntity2.setSurname("Doe");
        userEntity2.setLogin("jdoe");
        userEntity2.setPassword("mySecret");

        userRepository.save(userEntity);
        userRepository.save(userEntity2);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setName("Large Room");
        roomEntity.setLocation("1st floor");
        roomEntity.setNumberOfSeats(10);
        roomEntity.setHasProjector(true);
        roomEntity.setPhoneNumber("22-22-22-22");

        RoomEntity roomEntity2  = new RoomEntity();
        roomEntity2.setName("Medium Room");
        roomEntity2.setLocation("1st floor");
        roomEntity2.setNumberOfSeats(6);
        roomEntity2.setHasProjector(true);

        RoomEntity roomEntity3 = new RoomEntity();
        roomEntity3.setName("Small Room");
        roomEntity3.setLocation("2nd floor");
        roomEntity3.setNumberOfSeats(4);
        roomEntity3.setHasProjector(false);

        roomRepository.save(roomEntity);
        roomRepository.save(roomEntity2);
        roomRepository.save(roomEntity3);

    }
}
