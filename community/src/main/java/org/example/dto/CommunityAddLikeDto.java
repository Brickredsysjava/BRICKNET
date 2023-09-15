package org.example.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityAddLikeDto {

   private Boolean like;
}
