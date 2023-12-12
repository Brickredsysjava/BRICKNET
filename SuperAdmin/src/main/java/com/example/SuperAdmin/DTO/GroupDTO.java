package com.example.SuperAdmin.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class GroupDTO {
    private String groupId;

    private String groupName;

    private String admin;

    private List<String> members;


}
