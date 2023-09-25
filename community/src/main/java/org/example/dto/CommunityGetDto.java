package org.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.model.ContentDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityGetDto {

    private String Id;


    private String employee_code;

    private String title;


    private String description;


    private LocalDateTime date_time;

    private List<ContentDetails> contents;


    private Long likeCount;


    private Boolean adminVerified;

    private String verificationStatusMessage;

//    private Boolean adminVerificationStatus;


    private List<String> likedEmployee=new ArrayList<>();


}
