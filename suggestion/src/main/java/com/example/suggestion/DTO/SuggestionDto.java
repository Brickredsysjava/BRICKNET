package com.example.suggestion.DTO;


import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;



@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SuggestionDto {


    @Valid
    @NotNull(message = "ticket_id can't be null")
    @NotBlank(message = "ticket_id can't be blank")
    @NotEmpty(message = "ticket_id can't be empty")
    private String ticket_id;


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

    @NonNull
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

    @Column(name = "suggestion_date")
    @Valid
    @NotNull(message = "suggestionDate can't be null")
    @NotEmpty(message = "suggestionDate can't be empty")
    private LocalDate suggestionDate;

    private Boolean adminVerified;

    @Valid
    @NotNull(message = "verificationStatusMessage can't be null")
    @NotEmpty(message = "verificationStatusMessage can't be empty")
    private String verificationStatusMessage;


}
