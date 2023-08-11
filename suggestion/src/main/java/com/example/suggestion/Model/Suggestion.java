package com.example.suggestion.Model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "suggestions")
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;

    @NotNull
    private String subjectTitle;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Department department;


    @NotNull
    @Enumerated
    private Status status;

    @NotNull
    private int likeCount;

    @NotNull
    private int dislikeCount;

    private double likePercentage;
    private double dislikePercentage;

    @Column(name = "suggestion_date")
    private LocalDate suggestionDate;

}
