package com.example.suggestion.DTO;


import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SuggestionPostDto {

    @Valid
    @NotNull(message = "username can't be null")
    @NotBlank(message = "username can't be blank")
    @NotEmpty(message = "username can't be empty")
    private String username;

    @Valid
    @NotNull(message = "subjectTitle can't be null")
    @NotBlank(message = "subjectTitle can't be blank")
    @NotEmpty(message = "subjectTitle can't be empty")
    private String title;

    @Valid
    @NotNull(message = "description can't be null")
    @NotBlank(message = "description can't be blank")
    @NotEmpty(message = "description can't be empty")
    private String description;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Valid
    @NotNull(message = "employeeCode can't be null")
    @NotBlank(message = "employeeCode can't be blank")
    @NotEmpty(message = "employeeCode can't be empty")
    private String employeeCode;

    private LocalDateTime suggestionDateTime;
}
