package com.microservices.Broadcasting.Dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class GetBroadcastInfoDTO {

    private String message;

    private LocalDateTime start_time;

    private LocalDateTime end_time;

    private String type_of_event;

    private String filename;
}
