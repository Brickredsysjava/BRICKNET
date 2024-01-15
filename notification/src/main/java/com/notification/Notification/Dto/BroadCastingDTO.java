package com.notification.Notification.Dto;

import lombok.Data;
import org.springframework.aop.target.LazyInitTargetSource;

import java.util.List;

@Data
public class BroadCastingDTO {

    String setSubject;

    List<String> bcc;

    String text;
}
