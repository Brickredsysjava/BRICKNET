package com.example.SuperAdmin.DTO;

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
public class BankDetailsDTO {
    @NotBlank(message = "Account number is required")
    @Size(min = 8, max = 20, message = "Account number should be between 8 and 20 characters")
    @NotNull(message = "account number is required")
    private String accountNumber;

    @NotBlank(message = "Bank name is required")
    @NotNull(message = "Bank name is required")
    private String bankName;

    @NotBlank(message = "Branch name is required")
    @NotNull(message = "Branch name is required")
    private String branchName;

    @NotBlank(message = "IFSC code is required")
    @Pattern(regexp = "^[A-Z]{4}[0-9]{7}$", message = "Invalid IFSC code format")
    @NotNull(message = "IFSC code is required")
    private String ifscCode;

    @NotBlank(message = "PAN number is required")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number format")
    @NotNull(message = "PAN number is required")
    private String panNumber;
}

