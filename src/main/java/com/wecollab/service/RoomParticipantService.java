package com.wecollab.service;



import com.wecollab.domain.RoomParticipant;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoomParticipantService {
    public RoomParticipant saveRoomParticipant(RoomParticipant room_participant) throws Exception;

    public List<RoomParticipant> getAllRoomParticipationDetails();

    public  List<RoomParticipant> getRoomParticipationDetailsByUserId(int userId) throws Exception;

    public  List<RoomParticipant> getRoomRoomParticipationDetailsByRoomId(int roomId) throws Exception;

}
