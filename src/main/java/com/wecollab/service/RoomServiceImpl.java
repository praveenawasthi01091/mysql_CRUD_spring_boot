package com.wecollab.service;

import com.wecollab.domain.Room;
import com.wecollab.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
// mvn clean
// mvn spring-boot:run -Dspring.profiles.active=dummy
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        System.out.println("Inside actual impl");
        this.roomRepository=roomRepository;
    }



    @Override
    public Room saveRoom(Room room ) throws Exception {
        if(roomRepository.existsById(room.getId()))
            throw new Exception("User id is already exists");
        Room savedRoom= roomRepository.save(room);
        if(savedRoom == null)
            throw new Exception("Track id is already exists");
        return savedRoom;
    }

    @Override
    public List<Room> getAllRoom() {

        return roomRepository.findAll();
    }

    @Override
    public List<Room> getRoomByCompanyId(int companyId) throws Exception {
        List<Room>list= roomRepository.findBycompanyId(companyId);
        return list;
    }
}