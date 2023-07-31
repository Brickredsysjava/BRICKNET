package com.bricknet.communityservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "Community_Posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
@ToString


public class CommunityPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long post_id;


    private String employee_name;

//    @Lob
//   @Column(columnDefinition = "BLOB")
////    @Column(name = "profileImage",length = 1000)
//    private byte[] profileImage;

//    private String profileImage;


    private String profileImage;

    private String title;

    private String description;

    private Date date_time;

    private String content;


}
