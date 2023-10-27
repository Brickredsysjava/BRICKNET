package com.example.SuperAdmin.DTO;

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
    @NotBlank(message = "Date of birth is required")
    @NotNull(message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(message = "Nationality is required")
    @NotNull(message = "nationality is required")
    private String nationality;

    @NotBlank(message = "Place of birth is required")
    @NotNull(message = "Place of birth is required")
    private String placeOfBirth;

    @NotBlank(message = "Religion is required")
    @NotNull(message = "Religion is required")
    private String religion;

    @NotBlank(message = "Father's name is required")
    @NotNull(message = "Father's name is required")
    private String fatherName;

    @NotBlank(message = "International employee status is required")
    @NotNull(message = "International employee status is required")
    private String internationalEmployee;

    @NotBlank(message = "Marital status is required")
    @NotNull(message = "Marital status is required")
    private String maritalStatus;

    @NotBlank(message = "Physically challenged status is required")
    @NotNull(message = "Physically challenged status is required")
    private String physicallyChallenged;
}
