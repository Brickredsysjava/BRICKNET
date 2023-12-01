package com.bricknet.workflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2" , strategy = "org.hibernate.id.UUIDGenerator")
    private String taskId;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private  String employeeAssignedBy;
    private List<String> employeeAssignedTo = new ArrayList<>();
    private LocalDate estimatedStartDate;
    private LocalDate actualStartDate;
    private LocalDate estimatedEndDate;
    private LocalDate actualEndDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentTtaskId")
    @OrderColumn
    private List<Docs> docsList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentTtaskId")
    @OrderColumn
    private List<Task> subTasks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parenTtaskId")
    @OrderColumn
    private List<Message> discussion;


}
