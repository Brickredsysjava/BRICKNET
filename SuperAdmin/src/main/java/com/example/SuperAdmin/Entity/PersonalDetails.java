package com.example.SuperAdmin.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;



@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity

public class PersonalDetails {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;
    private String dateOfBirth;
    private String nationality;
    private String placeOfBirth;
    private String religion;
    private String fatherName;
    private String internationalEmployee;
    private String maritalStatus;
    private String physicallyChallenged;


}
