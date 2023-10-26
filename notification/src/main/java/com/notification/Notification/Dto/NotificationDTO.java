package com.notification.Notification.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationDTO {


    @Valid
    @NotNull(message = "message can't be null")
    @NotBlank(message = "message can't be blank")
    @NotEmpty(message = "message can't be empty")
    private String message;


    @Valid
    @NotNull(message = "timestamp can't be null")
    private LocalDateTime timestamp;
}
