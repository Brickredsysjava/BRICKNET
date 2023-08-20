package com.bricknet.workflow.service;

import com.bricknet.workflow.dto.NotificationDto;
import reactor.core.publisher.Mono;

public interface NotificationService {

    public void sendMail(String to, String subject, String text);

    public Mono<NotificationDto> fetchDataFromApi();

}
