package org.example.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityUpdateDto {

    @Valid
    @NotBlank(message = "postId is required")
    @NotEmpty(message = "postId is required")
    @NotNull(message = "postId is required")
    private String postId;

    @Valid
    @NotNull(message = "employeeCode is required")
    @NotEmpty(message = "employeeCode is required")
    @NotNull(message = "employeeCode is required")
    private String employeeCode;

    @NotNull(message = "Title is required")
    @NotEmpty(message = "title is required")
    @NotNull(message = "title is required")
    private String title;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "description is required")
    @NotNull(message = "description is required")
    private String description;

    private List<String> fileName;

}
