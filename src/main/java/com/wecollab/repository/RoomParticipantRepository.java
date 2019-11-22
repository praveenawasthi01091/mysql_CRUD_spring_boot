package com.wecollab.repository;


import com.wecollab.domain.RoomParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomParticipantRepository extends JpaRepository<RoomParticipant, Integer> {
    /* the implementation will be provided to us in run time*/
    @Query("from RoomParticipant where userId=?1")
    public List<RoomParticipant> findRoomParticipationByUserId(int userId);

    @Query("from RoomParticipant where roomId=?1")
    public List<RoomParticipant> findRoomParticipationByRoomId(int roomId);

}