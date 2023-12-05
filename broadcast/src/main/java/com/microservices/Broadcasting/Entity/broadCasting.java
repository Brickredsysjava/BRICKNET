package com.microservices.Broadcasting.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private String typeOfEvent;

    @Column
    private String fileName;
}
