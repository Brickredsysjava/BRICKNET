package com.attendanceApiForApp.attendanceApiForApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "record")
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private Date date;

}
