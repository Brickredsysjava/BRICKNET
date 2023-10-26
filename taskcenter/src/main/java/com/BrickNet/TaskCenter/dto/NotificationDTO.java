package com.BrickNet.TaskCenter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotBlank(message = "message can't be blank")
    @NotEmpty(message = "message can't be empty")
    private String message;

    @NotNull(message = "recipient can't be null")
    @NotBlank(message = "recipient can't be blank")
    @NotEmpty(message = "recipient can't be empty")
    private String recipient;

    @NotNull(message = "timeStamp can't be null")
    @NotBlank(message = "timeStamp can't be blank")
    @NotEmpty(message = "timeStamp can't be empty")
    private LocalDateTime timeStamp;

}
