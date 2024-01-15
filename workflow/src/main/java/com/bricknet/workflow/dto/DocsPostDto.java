package com.bricknet.workflow.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocsPostDto {
    private String content;
    private String employeeCode;
}
