package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityPostDeleteDto {

    @NotNull
    @NotBlank
    private String postId;

    @NotNull
    @NotBlank
    private String employeeCode;

}
