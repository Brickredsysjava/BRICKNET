package com.example.SuperAdmin.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Profile {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;
    private String gender;
    @Column(unique = true)
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String personalEmail;
    private String phoneNumber;
    private String password;
    private String companyEmail;
    private String designation;
    private String location;
    private String bloodGroup;
    private String department;
    private String reportingTo;
    private String grade;
    private String aContact;
    @Enumerated(EnumType.STRING)
    private Role role;

}
