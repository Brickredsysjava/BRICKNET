package com.bricknet.workflow.dto;

import com.bricknet.workflow.model.Status;
import com.bricknet.workflow.model.Task;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    @Valid
    @NotNull(message = "Please provide a valid title for workflow.")
    private String title;
    @Valid
    @NotNull(message = "These is a description about your workflow .")
    private String description;
    @NotNull
    private List<String> employeeAssignedTo;
    @Valid
    @NotNull(message = "Please provide the planned start date.")
    private LocalDate estimatedStartDate;
    @Valid
    @NotNull(message = "Please provide the planned end date.")
    private LocalDate estimatedEndDate;
}
