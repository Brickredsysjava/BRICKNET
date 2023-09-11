package com.microservices.Broadcasting.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Calendar;
import java.util.Date;


@Entity
@Table(name = "BroadCasting")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BroadCasting {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2" , strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column
    private String title;

    @Column
    private String receiverEmail;

    @Column
    private String message;

    @Column
    private String date;

    @Column
    private String startTime;

    @Column
    private String endTime;


    @Email
    @Column
    private String Cc;

    @Enumerated(EnumType.STRING)
    private EventType eventType;


    private String content;
    private String contentType;

}