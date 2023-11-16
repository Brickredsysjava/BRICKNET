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

   @NotNull(message = "The postId must not be null")
   @NotBlank(message = "The postId must not be blank")
   private String postId;

   @NotNull(message = "The employeeCode must not be null")
   @NotBlank(message = "The employeeCode must not be blank")
   private String employeeCode;

   private Boolean like;
}
