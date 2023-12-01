package com.bricknet.workflow.dto;

import com.bricknet.workflow.model.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TodoDto {
    private String title;
    private String description;
    private Status status;
    private String creatorId;
    private List<String> userAssigned;
    private LocalDate estimatedStartDate;
    private LocalDate estimatedEndDate;
}
