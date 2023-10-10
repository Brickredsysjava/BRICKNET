package org.example.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotNull(message = "postId is required")
    private String postId;

    @NotNull(message = "employeeCode is required")
    private String employeeCode;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Description is required")
    private String description;

    private List<String> fileName;

}
