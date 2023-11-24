package com.example.suggestion.DTO;

import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
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
public class GetSuggestionsDTO {

    private String ticket_id;

    private String username;

    private String title;

    private String description;

    private Department department;

    private Status status;

    private Long likeCount;

    private Long dislikeCount;

    private Double likePercentage;

    private Double dislikePercentage;

    private LocalDateTime suggestionDateTime;

    private Boolean adminVerified;

    private Boolean verificationStatusMessage;
}
