package com.example.suggestion.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Clob;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "suggestions")
public class Suggestion implements Comparable<Suggestion>{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2" , strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ticket_id", columnDefinition = "VARCHAR(255)")
    private String ticket_id;


    @NotNull
    private String username;

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


    private Boolean adminVerified;

    private String verificationStatusMessage;


    @Override
    public int compareTo(Suggestion suggestion) {
        // Compare the dates using the compareTo method of the Date class
        return suggestion.suggestionDate.compareTo(this.suggestionDate);
    }



}
