package com.bricknet.workflow.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocsDto {
    private String docsPath;
    private String userPic;
    private String userName;
}
