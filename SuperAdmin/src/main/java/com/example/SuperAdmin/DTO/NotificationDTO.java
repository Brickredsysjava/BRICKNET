package com.example.SuperAdmin.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    @NotNull
    private String message;

    @NotNull
    private String recipient;

    @NotNull
    private LocalDateTime timeStamp;

}