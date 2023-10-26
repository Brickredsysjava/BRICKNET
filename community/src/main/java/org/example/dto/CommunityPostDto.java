package org.example.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityPostDto {

  // @NotNull(message = "Employee code is required")
    private String employeeCode;

    @Valid
    @NotBlank(message = "Title is required")
    private String title;

    @Valid
    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must be at most 255 characters long")
    private String description;

    private List<String> fileName;


}
