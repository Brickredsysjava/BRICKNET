package com.example.SuperAdmin.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDTO {

    private String department;

    private String designation;

    private String location;

    private String reportingTo;

    private String grade;
}
