package com.bricknet.workflow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workId;
    private String title;
    private boolean visibility;
    private boolean active;
    private List<String> tags;
    private String description;
    private List<Long> userAssignedTeam = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    private Long creatorId;
    private LocalDate estimatedStartDate;
    private LocalDate actualStartDate;//auto after task started
    private LocalDate estimatedEndDate;
    private LocalDate actualEndDate;//auto after task ended
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    @OrderColumn
    private List<Docs> docsList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    @OrderColumn
    private List<Task> tasks;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    @OrderColumn
    private List<Message> discussion;
}
