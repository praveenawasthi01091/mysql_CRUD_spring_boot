package com.wecollab.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
//   domain or  model

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Room")
public class Room {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private  String agenda;
    @Column
    private int companyId;
    @Column
    private int adminId;

// @JsonFormat(pattern="yyyy-MM-dd")
    @Column
    private java.sql.Date sqlDate;
// @JsonFormat(pattern="HH:mm:ss")
    @Column
    private java.sql.Time sqlTime;



}
