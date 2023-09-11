package com.BrickNet.TaskCenter.dto;

import com.BrickNet.TaskCenter.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoriesDTO {

    private String title;

    private String description;

    private Status status;

    private String creatorId;

    private List<String> userAssigned;

    private LocalDate estimatedStartDate;

    private LocalDate estimatedEndDate;

}
