package com.attendanceApiForApp.attendanceApiForApp.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emp_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

   @Column(nullable = false)
    private  String password;


}

