package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminPostVerificationDto {

    @NotBlank
    @NotNull(message = "postId is required")
    private String postId;

    @NotNull(message = "adminVerified is required")
    private Boolean adminVerified;
}
