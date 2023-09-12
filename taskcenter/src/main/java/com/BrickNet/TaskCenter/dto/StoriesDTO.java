package com.BrickNet.TaskCenter.dto;

import com.BrickNet.TaskCenter.model.Status;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Status status;

    @NotNull
    private String creatorId;

    @NotNull
    private List<String> userAssigned;

    @NotNull
    private LocalDate estimatedStartDate;

    @NotNull
    private LocalDate estimatedEndDate;

}
