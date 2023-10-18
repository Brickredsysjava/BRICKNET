package com.attendanceApiForApp.attendanceApiForApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.Date;
import java.util.UUID;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "record_id", columnDefinition = "VARCHAR(255)")
    private String record_id;

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private Date date;
    @Column(nullable = true)
    private String durations;
    @Column(nullable = true)
    private String timezone;
    @Column(nullable = false)
    private boolean status = false;
    @Column(nullable = true)
    private boolean timeZoneSelected = false;

}
