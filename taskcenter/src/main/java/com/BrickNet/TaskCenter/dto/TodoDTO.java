package com.BrickNet.TaskCenter.dto;

import com.BrickNet.TaskCenter.model.Priority;
import com.BrickNet.TaskCenter.model.Status;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
@Getter
public class TodoDTO {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

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
    @NotNull(message = "Estimated Start Date can't be null")
    @NotEmpty(message = "Estimated Start Date can't be empty")
    private LocalDate estimatedStartDate;

    @Valid
    @NotNull(message = "Actual Start Date can't be null")
    @NotEmpty(message = "Actual Start Date can't be empty")
    private LocalDate actualStartDate;

    @Valid
    @NotNull(message = "Estimate End Date can't be null")
    @NotEmpty(message = "Estimate End Date can't be empty")
    private LocalDate estimatedEndDate;

    @Valid
    @NotNull(message = "Actual End Date can't be null")
    @NotEmpty(message = "Actual End Date can't be empty")
    private LocalDate actualEndDate;

    @Valid
    @NotNull(message = "Status can't be null")
    @NotBlank(message = "Status can't be blank")
    @NotEmpty(message = "Status can't be empty")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Valid
    @NotNull(message = "Priority can't be null")
    @NotBlank(message = "Priority can't be blank")
    @NotEmpty(message = "Priority can't be empty")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Valid
    @NotNull(message = "Employee Assigned By can't be null")
    @NotBlank(message = "Employee Assigned By can't be blank")
    @NotEmpty(message = "Employee Assigned By can't be empty")
    private String employeeAssignedBy;

    @Valid
    @NotNull(message = "Employee Assigned To can't be null")
    @NotEmpty(message = "Employee Assigned To can't be empty")
    private List<String> employeeAssignedTo;
}
