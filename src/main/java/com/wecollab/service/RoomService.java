package com.wecollab.service;


import com.wecollab.domain.Room;

import java.util.List;

public interface RoomService {
    public Room saveRoom(Room room) throws Exception;

    public List<Room> getAllRoom();

    public  List<Room> getRoomByCompanyId(int companyId) throws Exception;

}
