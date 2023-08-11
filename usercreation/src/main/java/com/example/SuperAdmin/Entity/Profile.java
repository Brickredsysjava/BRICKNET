package com.example.SuperAdmin.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
   private Long userId;
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String personalEmail;
    private String phoneNumber;
    private String password;
    private String companyEmail;
    private String designation;
    private String location;
    private String BloodGroup;


}