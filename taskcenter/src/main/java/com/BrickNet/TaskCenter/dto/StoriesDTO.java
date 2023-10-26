package com.BrickNet.TaskCenter.dto;

import com.BrickNet.TaskCenter.model.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @Valid
    @NotNull(message = "Title can't be null")
    @NotBlank(message = "Title can't be blank")
    @NotEmpty(message = "Title can't be empty")
    private String title;

    @Valid
    @NotNull(message = "Description can't be null")
    @NotBlank(message = "Description can't be blank")
    @NotEmpty(message = "Description can't be empty")
    private String description;

    @Valid
    @NotNull(message = "Status can't be null")
    @NotEmpty(message = "Status can't be empty")
    private Status status;

    @Valid
    @NotNull(message = "Employee Assigned By can't be null")
    @NotBlank(message = "Employee Assigned By can't be blank")
    @NotEmpty(message = "Employee Assigned By can't be empty")
    private String employeeAssignedBy;

    @Valid
    @NotNull(message = "Employee Assigned To can't be null")
    @NotBlank(message = "Employee Assigned To can't be blank")
    @NotEmpty(message = "Employee Assigned To can't be empty")
    private List<String> employeeAssignedTo;

    @Valid
    @NotNull(message = "Estimate Start Date can't be null")
    private LocalDate estimatedStartDate;

    @Valid
    @NotNull(message = "Employee End Date can't be null")
    private LocalDate estimatedEndDate;

}
