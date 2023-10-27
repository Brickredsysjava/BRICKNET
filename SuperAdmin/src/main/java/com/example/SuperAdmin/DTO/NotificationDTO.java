package com.example.SuperAdmin.DTO;

import jakarta.validation.Valid;
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

    @Valid
    @NotNull
    private String message;

    @Valid
    @NotNull
    private String recipient;

    @Valid
    @NotNull
    private LocalDateTime timeStamp;

}