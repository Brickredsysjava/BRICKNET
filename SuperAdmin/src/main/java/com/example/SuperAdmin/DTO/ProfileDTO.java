package com.example.SuperAdmin.DTO;

import com.example.SuperAdmin.Entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @NotBlank(message = "Gender is required")
    @NotNull(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Employee code is required")
    @NotNull(message = "Employee code is required")
    private String employeeCode;

    @NotNull(message = "profile image is required")
    @NotBlank(message = "profile image is required")
    private List<String> profileImage;

    @NotBlank(message = "First name is required")
    @NotNull(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @NotNull(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Personal email is required")
    @NotNull(message = "Personal email is required")
    @Email(message = "Invalid email format")
    private String personalEmail;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number format")
    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    @NotNull(message = "Password is required")
    private String password;

    @NotBlank(message = "Company email is required")
    @Email(message = "Invalid email format")
    @NotNull(message = "Company email is required")
    private String companyEmail;

    @NotBlank(message = "Designation is required")
    @NotNull(message = "Designation is required")
    private String designation;

    @NotBlank(message = "Location is required")
    @NotNull(message = "Location is required")
    private String location;

    @NotBlank(message = "Blood group is required")
    @NotNull(message = "Blood group is required")
    private String bloodGroup;

    @NotBlank(message = "Department is required")
    @NotNull(message = "Department is required")
    private String department;

    @NotNull(message = "reporting To entity is required")
    @NotBlank(message = "reporting To is required")
    private String reportingTo;

    @NotNull(message = "role is required")
    @Enumerated(EnumType.STRING)
    private Role role;

}
