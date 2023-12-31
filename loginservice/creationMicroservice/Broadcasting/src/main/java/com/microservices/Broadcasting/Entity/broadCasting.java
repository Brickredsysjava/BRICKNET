package com.microservices.Broadcasting.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "BroadCasting")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class broadCasting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;

    @Column
    private String email;

    @Column
    private String message;

    @Column
    private String selectedDate;

    @Column
    private String time;

    @Column
    private String selectedOption;

    @Column
    private String typeOfEvent;
}
