package com.example.suggestion.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Clob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
    @Column(name = "post_id", columnDefinition = "VARCHAR(255)")
    private String  ticket_Id;

    private String employeeCode;

    private String username;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime dateTime;

    private Boolean adminVerified;

    private String verificationStatusMessage;

    private List<String> likedEmployee=new ArrayList<>();

    private List<String> disLikedEmployee = new ArrayList<>();

    public List<String> getLikedEmployee() {
        return likedEmployee != null ? likedEmployee : new ArrayList<>(); }

    @Override
    public int compareTo(Suggestion suggestion) {
        // Compare the dates using the compareTo method of the Date class
        return suggestion.dateTime.compareTo(this.dateTime);
    }



}
