package com.example.SuperAdmin.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {

    @Valid
    @NotBlank(message = "Type of education is required")
    @NotNull(message = "Type of education is required")
    private String typeOfEducation;

    @Valid
    @NotBlank(message = "Passing year is required")
    @Size(min = 4, max = 4, message = "Passing year should be 4 characters long")
    @Pattern(regexp = "^[0-9]{4}$", message = "Invalid passing year format")
    @NotNull(message = "Passing year is required")
    private String passingYear;

    @Valid
    @NotBlank(message = "Percentage is required")
    @Pattern(regexp = "^\\d{1,2}(\\.\\d{1,2})?$", message = "Invalid percentage format")
    @NotNull(message = "Percentage is required")
    private String percentage;

    @Valid
    @NotBlank(message = "Board is required")
    @NotNull(message = "Board is required")
    private String board;

    @Valid
    @NotBlank(message = "Institute name is required")
    @NotNull(message = "Institute name is required")
    private String instituteName;

    @Valid
    @NotBlank(message = "Place is required")
    @NotNull(message = "Place is required")
    private String place;
}

