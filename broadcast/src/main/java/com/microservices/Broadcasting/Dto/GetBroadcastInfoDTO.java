package com.microservices.Broadcasting.Dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetBroadcastInfoDTO {

    private String message;

    private String start_time;

    private String end_time;

    private String type_of_event;
}
