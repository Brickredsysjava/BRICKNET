package com.bricknet.communityservice.dto;

import lombok.*;

import java.sql.Blob;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityPostDto {

    private String employee_name;

//    @Lob
////    @Column(columnDefinition = "BLOB")
//    @Column(name = "profileImage",length = 1000)
//    private byte[] profileImage;

//    private String profileImage;

    private String title;

    private String description;

    private Date date_time;

    private String profileImage;

    private String content;
}
