package com.wecollab.repository;


import com.wecollab.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    /* the implementation will be provided to us in run time*/
    @Query("from Room where companyId=?1")
    public List<Room> findBycompanyId(int companyId);

}