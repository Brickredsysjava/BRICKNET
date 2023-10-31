package com.example.suggestion.DTO;

import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.time.LocalDate;

public class getSuggestionsDTO {

    private String ticket_id;

    private String username;

    private String subjectTitle;

    private String description;

    private Department department;

    private Status status;

    private int likeCount;

    private int dislikeCount;

    private double likePercentage;

    private double dislikePercentage;

    private LocalDate suggestionDate;

    private Boolean adminVerified;

    private String verificationStatusMessage;
}
