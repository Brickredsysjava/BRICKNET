package com.bricknet.workflow.dto;

public class EmailDto {
    private  String message;
    private String recipient;

    public EmailDto(String message, String recipient) {
        this.message = message;
        this.recipient = recipient;
    }
}
