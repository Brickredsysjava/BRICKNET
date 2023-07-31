package com.attendanceApiForApp.attendanceApiForApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "check_ins")
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long checkin_id;

    @ManyToOne
    @JoinColumn(name = "record_id", nullable = false)
    private Records record;

    @Column(nullable = false)
    private String checkinTime;
    @Column(nullable = false)
    private String timezone;



}

