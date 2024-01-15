package com.microservices.Broadcasting.Dto;

import lombok.Data;

import java.util.List;

@Data
public class BroadCastingDTO {

    String setSubject;

    List<String> bcc;

    String text;
}
