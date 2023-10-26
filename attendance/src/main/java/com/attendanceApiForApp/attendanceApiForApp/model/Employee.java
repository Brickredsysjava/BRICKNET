package com.attendanceApiForApp.attendanceApiForApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "auto_id", columnDefinition = "VARCHAR(255)")
    private String auto_id;

    @Column
    private String emp_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

   @Column(nullable = false)
    private  String password;

}

