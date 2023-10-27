package com.example.SuperAdmin.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class TimeLine {

    @Valid
    @NotBlank(message = "designation is required")
    @NotNull(message = "designation is required")
    private String designation;

    @Valid
    @NotBlank(message = "reporting To is required")
    @NotNull(message = "reporting To is required")
    private String reportingTo;

    @Valid
    @NotBlank(message = "department is required")
    @NotNull(message = "department is required")
    private String department;

    @Valid
    @NotBlank(message = "location is required")
    @NotNull(message = "location is required")
    private String location;
}