package com.bricknet.workflow.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessagePostDto {
    private String content;
    private String employeeCode;
}
