package com.BrickNet.TaskCenter.dto;

import com.BrickNet.TaskCenter.model.Priority;
import com.BrickNet.TaskCenter.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
@Getter
public class AllDTO {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private LocalDate estimatedStartDate;

    @NotNull
    private LocalDate estimatedEndDate;

    @NotNull
    private String employeeAssignedBy;

    @NotNull
    private List<String> employeeAssignedTo;

}
