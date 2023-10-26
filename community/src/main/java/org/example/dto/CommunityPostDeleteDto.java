package org.example.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityPostDeleteDto {

    @Valid
    @NotBlank(message = "postId is required")
    @NotEmpty(message = "postId is required")
    @NotNull(message = "postId is required")
    private String postId;

    @Valid
    @NotNull(message = "employeeCode is required")
    private String employeeCode;

}
