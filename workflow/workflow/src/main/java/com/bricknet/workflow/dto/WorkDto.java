package com.bricknet.workflow.dto;

import com.bricknet.workflow.model.Status;
import com.bricknet.workflow.model.Task;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkDto {
    private String title;
    private boolean visibility;
    private List<String> tags;
    private String description;
    private List<Long> userAssignedTeam = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    private Long creatorId;
    private LocalDate estimatedStartDate;
    private LocalDate estimatedEndDate;
    private List<Task> tasks;
}
