package com.example.SuperAdmin.DTO;

import com.example.SuperAdmin.Entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    @Valid
    @NotBlank(message = "Gender is required")
    @NotNull(message = "Gender is required")
    private String gender;

    @Valid
    @NotBlank(message = "Employee code is required")
    @NotNull(message = "Employee code is required")
    private String employeeCode;

    @Valid
    @NotNull(message = "profile image is required")
    private List<String> profileImage;

    @Valid
    @NotBlank(message = "First name is required")
    @NotNull(message = "First name is required")
    private String firstName;

    @Valid
    @NotBlank(message = "Last name is required")
    @NotNull(message = "Last name is required")
    private String lastName;

    @Valid
    @NotBlank(message = "Personal email is required")
    @NotNull(message = "Personal email is required")
    @Email(message = "Invalid email format")
    private String personalEmail;

    @Valid
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number format")
    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @Valid
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    @NotNull(message = "Password is required")
    private String password;

    @Valid
    @NotBlank(message = "Company email is required")
    @Email(message = "Invalid email format")
    @NotNull(message = "Company email is required")
    private String companyEmail;

    @Valid
    @NotBlank(message = "Designation is required")
    @NotNull(message = "Designation is required")
    private String designation;

    @Valid
    @NotBlank(message = "Location is required")
    @NotNull(message = "Location is required")
    private String location;

    @Valid
    @NotBlank(message = "Blood group is required")
    @NotNull(message = "Blood group is required")
    private String bloodGroup;

    @Valid
    @NotBlank(message = "Department is required")
    @NotNull(message = "Department is required")
    private String department;

    @Valid
    @NotNull(message = "reporting To entity is required")
    @NotBlank(message = "reporting To is required")
    private String reportingTo;

    @Valid
    @NotNull(message = "Grade is required")
    @NotBlank(message = "Grade is required")
    private String grade;

    @Valid
    @NotNull(message = "role is required")
    @Enumerated(EnumType.STRING)
    private Role role;

}
