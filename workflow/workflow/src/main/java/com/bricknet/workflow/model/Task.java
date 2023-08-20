package com.bricknet.workflow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    private String title;
    private String description;
    private List<Long> userAssigned;
    private Status status;
    private Long creatorId;
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
