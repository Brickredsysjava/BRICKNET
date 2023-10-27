package com.example.SuperAdmin.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    @NotNull(message = "Type of Address is required")
    @NotBlank(message = "Type of Address is required")
    private String typeOfAddress;

    @NotBlank(message = "Street Address is required")
    @NotNull(message = "Street Address is required")
    private String streetAddress;

    @NotBlank(message = "apartment is required")
    @NotNull(message = "apartment is required")
    private String apartment;

    @NotBlank(message = "District is required")
    @NotNull(message = "District is required")
    private String district;

    @NotBlank(message = "City is required")
    @NotNull(message = "City is required")
    private String city;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "\\d{6}", message = "Pincode should be a 6-digit number")
    @NotNull(message = "pincode is required")
    private String pincode;

    @NotBlank(message = "State is required")
    @NotNull(message = "state is required")
    private String state;

    @NotBlank(message = "Country is required")
    @NotNull(message = "Country is required")
    private String country;
}
