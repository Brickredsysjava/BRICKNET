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
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
@Getter
public class PostTodoDTO {

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
    @NotNull(message = "Estimated Start Date can't be null or empty")
    private String estimatedStartDate;

    @Valid
    @NotNull(message = "Estimate End Date can't be null or empty")
    private String estimatedEndDate;

    @Valid
    @NotNull(message = "Priority can't be null or empty")
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
