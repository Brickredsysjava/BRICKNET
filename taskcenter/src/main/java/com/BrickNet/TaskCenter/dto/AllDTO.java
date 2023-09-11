package com.BrickNet.TaskCenter.dto;

import com.BrickNet.TaskCenter.model.Priority;
import com.BrickNet.TaskCenter.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
@Getter
public class AllDTO {

    private String taskName;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate estimatedStartDate;

    private LocalDate estimatedEndDate;

    private String assignedBy;

    private String assignedTo;

}
