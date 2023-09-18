package com.BrickNet.TaskCenter.dto;

import com.BrickNet.TaskCenter.model.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoriesDTO {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Status status;

    @NotNull
    private String employeeAssignedBy;

    @NotNull
    private List<String> employeeAssignedTo;

    @NotNull
    private LocalDate estimatedStartDate;

    @NotNull
    private LocalDate estimatedEndDate;

}
