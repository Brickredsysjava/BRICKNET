package com.microservices.Broadcasting.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private List<String> email;

    @Column
    private String message;

    @Column
    private String selectedDate;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private String selectedOption;

    @Column
    private String typeOfEvent;

    @Column
    private String fileName;
}
