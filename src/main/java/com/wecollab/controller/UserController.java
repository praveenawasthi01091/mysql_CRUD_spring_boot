package com.wecollab.controller;

import com.wecollab.domain.Room;
import com.wecollab.domain.RoomParticipant;
import com.wecollab.domain.RoomUser;
import com.wecollab.domain.User;
import com.wecollab.service.RoomParticipantService;
import com.wecollab.service.RoomService;
import com.wecollab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@PropertySources({
        //@PropertySource(value = "classpath:application-dev.properties"),
        @PropertySource(value = "classpath:application.properties")
        })

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1")
public class UserController {
    //@Qualifier("userServiceDummyImpl")
    UserService userService;
    RoomService roomService;
    RoomParticipantService roomParticipantService;
    @Autowired
    public UserController( UserService userService, RoomService roomService, RoomParticipantService roomParticipantService)
    {
        this.userService=userService;
        this.roomService=roomService;
        this.roomParticipantService=roomParticipantService;
    }


    /* create new resource -- post*/
    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
       // trackService.saveTrack(track);
        try {
            return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            System.out.println("inside Message method");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /*  Show all resources */
    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllTrack() throws Exception{

            return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);

    }
    /* Find given resource by cpmpany id */
    @GetMapping("getUserByCompanyId/{companyId}")
    public ResponseEntity<?> getTrackByName( @PathVariable int companyId) throws Exception{
        try {
        return new ResponseEntity<List<User>>( userService.getUserByCompanyId(companyId), HttpStatus.OK); }
       catch (Exception e)
        {
           return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
     }
    }

    /* roomservice methods*/

    /* create new resource -- post*/
    @PostMapping("/saveRoom")
    public ResponseEntity<?> saveRoom(@RequestBody Room room) throws Exception {
        // trackService.saveTrack(track);
        try {
            return new ResponseEntity<Room>(roomService.saveRoom(room), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            System.out.println("inside Message method");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    /* add in 2 tables*/
    /* create new resource -- post*/
    @PostMapping(value = "/saveRoomAndUsers", consumes = "application/json")
    public ResponseEntity<?> saveRoomandParticipant(@RequestBody RoomUser roomUser) throws Exception {
        // trackService.saveTrack(track);
        try {
            System.out.println("Inside new method");
            Room room=new Room();
            room.setName(roomUser.getName());
            room.setAdminId(roomUser.getAdminId());
            room.setAgenda(roomUser.getAgenda());
            String date1=roomUser.getSqlDate();
            Date date= Date.valueOf(date1);
            room.setSqlDate(date);
            room.setCompanyId(roomUser.getCompanyId());

            String time11=roomUser.getSqlTime();
            Time time=Time.valueOf(time11);
            room.setSqlTime(time);

            roomService.saveRoom(room);
            System.out.println(roomUser);

            /* 2nd part*/

            List<Integer> userIds=roomUser.getuIds();
            System.out.println(userIds.size());
            for(int i=0;i<userIds.size();i++)
            {
                RoomParticipant roomParticipant= new RoomParticipant();
                roomParticipant.setRoomId(room.getId());
                roomParticipant.setUserId(userIds.get(i));
                roomParticipantService.saveRoomParticipant(roomParticipant);
            }

            return new ResponseEntity<Room>(room, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            System.out.println("inside error method");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /*  Show all resources */
    @GetMapping("/getAllRoom")
    public ResponseEntity<?> getAllRoom() throws Exception{

        return new ResponseEntity<List<Room>>(roomService.getAllRoom(), HttpStatus.OK);

    }
    /* Find given resource by cpmpany id */
    @GetMapping("getRoomByCompanyId/{companyId}")
    public ResponseEntity<?> getRoomByName( @PathVariable int companyId) throws Exception{
        try {
            return new ResponseEntity<List<Room>>( roomService.getRoomByCompanyId(companyId), HttpStatus.OK); }
        catch (Exception e)
        {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    /* room participation methods*/

    /* create new resource -- post*/
    @PostMapping("/saveRoomParticipation")
    public ResponseEntity<?> saveRoom(@RequestBody RoomParticipant roomParticipant) throws Exception {
        // trackService.saveTrack(track);
        try {
            return new ResponseEntity<RoomParticipant>(roomParticipantService.saveRoomParticipant(roomParticipant), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            System.out.println("inside Message method");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /*  Show all resources */
    @GetMapping("/getAllRoomParticipant")
    public ResponseEntity<?> getAllRoomParticipation() throws Exception{

        return new ResponseEntity<List<RoomParticipant>>(roomParticipantService.getAllRoomParticipationDetails(), HttpStatus.OK);

    }
    /* Find given resource by room id */
    @GetMapping("getRoomParticipantByRoomId/{roomId}")
    public ResponseEntity<?> getRoomParticipantByRoomId( @PathVariable int roomId) throws Exception{
        try {
            return new ResponseEntity<List<RoomParticipant>>( roomParticipantService.getRoomRoomParticipationDetailsByRoomId(roomId), HttpStatus.OK); }
        catch (Exception e)
        {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    /* Find given resource by user id */
    @GetMapping("getRoomParticipantByUserId/{userId}")
    public ResponseEntity<?> getRoomParticipantByUserId( @PathVariable int userId) throws Exception{
        try {
            return new ResponseEntity<List<RoomParticipant>>( roomParticipantService.getRoomParticipationDetailsByUserId(userId), HttpStatus.OK); }
        catch (Exception e)
        {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

}
