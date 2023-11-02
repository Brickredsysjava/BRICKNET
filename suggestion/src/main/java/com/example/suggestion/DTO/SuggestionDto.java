package com.example.suggestion.DTO;


import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.sql.Clob;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SuggestionDto {

    @Valid
    @NotNull(message = "username can't be null")
    @NotBlank(message = "username can't be blank")
    @NotEmpty(message = "username can't be empty")
    private String username;

    @Valid
    @NotNull(message = "subjectTitle can't be null")
    @NotBlank(message = "subjectTitle can't be blank")
    @NotEmpty(message = "subjectTitle can't be empty")
    private String subjectTitle;

    @Valid
    @NotNull(message = "description can't be null")
    @NotBlank(message = "description can't be blank")
    @NotEmpty(message = "description can't be empty")
    private String description;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated
    private Status status;

    @Valid
    @NotNull(message = "likeCount can't be null")
    private int likeCount;

    @Valid
    @NotNull(message = "dislikeCount can't be null")
    private int dislikeCount;


    @Valid
    @NotNull(message = "likePercentage can't be null")
    private double likePercentage;

    @Valid
    @NotNull(message = "dislikePercentage can't be null")
    private double dislikePercentage;

    private LocalDateTime suggestionDateTime;

    private Boolean adminVerified;

    private String verificationStatusMessage;
}
