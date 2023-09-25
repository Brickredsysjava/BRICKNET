package com.BrickNet.TaskCenter.dto;

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

    @NotNull(message = "message can't be null")
    private String message;

    @NotNull(message = "recipient can't be null")
    private String recipient;

    @NotNull(message = "timeStamp can't be null")
    private LocalDateTime timeStamp;

}
