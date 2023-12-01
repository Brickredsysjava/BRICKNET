package com.bricknet.workflow.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WorkFlowDto {
    @Valid
    @NotNull(message = "Please provide a valid employeeId.")
    private String employeeId;
    @Valid
    @NotNull(message = "Please provide a valid title for workflow.")
    private String title;
    @Valid
    private boolean visibility;
    @Valid
    private List<String> tags;
    @Valid
    @NotNull(message = "These is a description about your workflow .")
    private String description;
    @Valid
    private List<String> employeeAssignedTo = new ArrayList<>();
    @Valid
    @NotNull(message = "Please provide the planned start date.")
    private LocalDate estimatedStartDate;
    @Valid
    @NotNull(message = "Please provide the planned end date.")
    private LocalDate estimatedEndDate;
}
