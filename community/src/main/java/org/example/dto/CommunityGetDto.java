package org.example.dto;

import lombok.*;

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

    private String postId;

    private String employeeCode;

    private String username;

    private String title;


    private String description;


    private LocalDateTime dateTime;

    private List<String> fileName;


    private Long likeCount;


    private Boolean adminVerified;

    private String verificationStatusMessage;


    private List<String> likedEmployee=new ArrayList<>();


}
