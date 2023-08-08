package com.example.suggestion.DTO;


import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SuggestionDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;


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


}
