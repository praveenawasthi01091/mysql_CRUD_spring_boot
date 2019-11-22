package com.wecollab.service;


import com.wecollab.domain.RoomParticipant;
import com.wecollab.repository.RoomParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
// mvn clean
// mvn spring-boot:run -Dspring.profiles.active=dummy
public class RoomParticipationServiceImpl implements RoomParticipantService {
    private RoomParticipantRepository roomParticipantRepository;

    @Autowired
    public RoomParticipationServiceImpl(  RoomParticipantRepository roomParticipantRepository) {
        System.out.println("Inside actual impl");
        this.roomParticipantRepository=roomParticipantRepository;
    }



    @Override
    public RoomParticipant saveRoomParticipant(RoomParticipant room_participant) throws Exception{
        if(roomParticipantRepository.existsById(room_participant.getId()))
            throw new Exception("User id is already exists");
        RoomParticipant savedRoomParticipant = roomParticipantRepository.save(room_participant);
        if(savedRoomParticipant == null)
            throw new Exception("Track id is already exists");
        return savedRoomParticipant;
    }

    @Override
    public List<RoomParticipant> getAllRoomParticipationDetails() {

        return roomParticipantRepository.findAll();
    }

    @Override
    public List<RoomParticipant> getRoomParticipationDetailsByUserId(int userId) throws Exception {
        List<RoomParticipant>list= roomParticipantRepository.findRoomParticipationByUserId(userId);
        return list;
    }

    @Override
    public List<RoomParticipant> getRoomRoomParticipationDetailsByRoomId(int roomId) throws Exception {
        List<RoomParticipant>list= roomParticipantRepository.findRoomParticipationByRoomId(roomId);
        return list;
    }

}