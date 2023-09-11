package com.microservices.Broadcasting.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationDto {

    private String recipient;

    private String message;
    private LocalDateTime timeStamp;
}
