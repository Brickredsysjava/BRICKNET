package com.example.SuperAdmin.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class TimeLine {
    private String designation;
    private String reportingTo;
    private String department;
    private String location;
}