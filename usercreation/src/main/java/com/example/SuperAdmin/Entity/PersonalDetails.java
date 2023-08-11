package com.example.SuperAdmin.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class PersonalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long userId;
    private String dateOfBirth;
    private String nationality;
    private String placeOfBirth;
    private String religion;
    private String fatherName;
    private String internationalEmployee;
    private String maritalStatus;
    private String physicallyChallenged;


}
