package com.example.suggestion.DTO;


import com.example.suggestion.Model.Department;
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticket_id;

    @NotEmpty
    private String subjectTitle;

    @NotEmpty
    private String description;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Department department;


}
