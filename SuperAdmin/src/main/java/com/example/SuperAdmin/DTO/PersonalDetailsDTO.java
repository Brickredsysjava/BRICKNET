package com.example.SuperAdmin.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetailsDTO {

    @Valid
    @NotBlank(message = "Date of birth is required")
    @NotNull(message = "Date of birth is required")
    private String dateOfBirth;

    @Valid
    @NotBlank(message = "Nationality is required")
    @NotNull(message = "nationality is required")
    private String nationality;

    @Valid
    @NotBlank(message = "Place of birth is required")
    @NotNull(message = "Place of birth is required")
    private String placeOfBirth;

    @Valid
    @NotBlank(message = "Religion is required")
    @NotNull(message = "Religion is required")
    private String religion;

    @Valid
    @NotBlank(message = "Father's name is required")
    @NotNull(message = "Father's name is required")
    private String fatherName;

    @Valid
    @NotBlank(message = "International employee status is required")
    @NotNull(message = "International employee status is required")
    private String internationalEmployee;

    @Valid
    @NotBlank(message = "Marital status is required")
    @NotNull(message = "Marital status is required")
    private String maritalStatus;

    @Valid
    @NotBlank(message = "Physically challenged status is required")
    @NotNull(message = "Physically challenged status is required")
    private String physicallyChallenged;
}
