package com.example.suggestion.DTO;


import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SuggestionDto {


    private Long ticket_id;

    @NotEmpty
    private String subjectTitle;

    @NotEmpty
    private String description;


    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated
    private Status status;



    private int likeCount;
    private int dislikeCount;



}
