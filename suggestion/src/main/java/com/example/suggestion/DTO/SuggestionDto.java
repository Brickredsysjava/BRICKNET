package com.example.suggestion.DTO;


import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import jakarta.persistence.*;
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

    private String ticket_id;

    @NotNull
    private String username;

    @NotEmpty
    private String subjectTitle;

    @NotEmpty
    private String description;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Department department;

    @NonNull
    @Enumerated
    private Status status;

    @NonNull
    private int likeCount;

    @NonNull
    private int dislikeCount;

    private double likePercentage;

    private double dislikePercentage;

    @Column(name = "suggestion_date")
    private LocalDate suggestionDate;

    private Boolean adminVerified;

    private String verificationStatusMessage;


}
