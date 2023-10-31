package com.example.suggestion.DTO;

import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import lombok.*;

import java.sql.Clob;
import java.time.LocalDate;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetSuggestionsDTO {

    private String ticket_id;

    private String username;

    private String subjectTitle;

    private Clob description;

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
