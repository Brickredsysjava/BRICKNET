package com.BrickNet.TaskCenter.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class Todo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    private String title;

    private String description;

    private LocalDateTime estimatedStartDate;

    private LocalDateTime actualStartDate;

    private LocalDateTime estimatedEndDate;

    private LocalDateTime actualEndDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String employeeAssignedBy;

    private List<String> employeeAssignedTo;

}
