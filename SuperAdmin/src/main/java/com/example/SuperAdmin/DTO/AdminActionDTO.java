package com.example.SuperAdmin.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminActionDTO {

   private String groupId;
   private String adminEmployeeCode;
   private String memberEmployeeCode;
   private Boolean action;

}
