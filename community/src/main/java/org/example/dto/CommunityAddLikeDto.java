package org.example.dto;

import jakarta.validation.constraints.AssertTrue;
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
public class CommunityAddLikeDto {

   @NotNull
   @NotBlank
   private String postId;

   @NotNull
   @NotBlank
   private String employeeCode;

   @NotNull(message = "The boolean field must not be null")
   @AssertTrue(message = "The boolean field must be either true or false")
   private Boolean like;
}
