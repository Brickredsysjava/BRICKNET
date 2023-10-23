package com.attendanceApiForApp.attendanceApiForApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "timeSheet")
public class TimeSheet {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "timesheet_id", columnDefinition = "VARCHAR(255)")
    private String timesheet_id;

    @ElementCollection
    private List<String> Task;
    @Column
    private LocalDate dates;
    @Column
    private String durations;
    @Column
    private boolean status = false;
    @Column
    private  String TypeofDay;

}
