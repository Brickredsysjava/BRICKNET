package com.example.SuperAdmin.DTO;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Type of education is required")
    private String typeOfEducation;

    @NotBlank(message = "Passing year is required")
    @Size(min = 4, max = 4, message = "Passing year should be 4 characters long")
    @Pattern(regexp = "^[0-9]{4}$", message = "Invalid passing year format")
    private String passingYear;

    @NotBlank(message = "Percentage is required")
    @Pattern(regexp = "^\\d{1,2}(\\.\\d{1,2})?$", message = "Invalid percentage format")
    private String percentage;

    @NotBlank(message = "Board is required")
    private String board;

    @NotBlank(message = "Institute name is required")
    private String instituteName;

    @NotBlank(message = "Place is required")
    private String place;
}
