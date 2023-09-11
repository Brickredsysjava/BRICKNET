package com.microservices.Broadcasting.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.microservices.Broadcasting.Entity.EventType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Calendar;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BroadcastingDto {
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


}
