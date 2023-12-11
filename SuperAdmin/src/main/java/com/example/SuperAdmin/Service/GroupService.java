package com.example.SuperAdmin.Service;


import com.example.SuperAdmin.DTO.AdminActionDTO;
import com.example.SuperAdmin.DTO.GroupDTO;
import com.example.SuperAdmin.Entity.Group;

import java.util.List;

public interface GroupService {
    List<String> findByMembers(String groupId) throws Exception;

    GroupDTO createGroup(GroupDTO groupDTO) throws Exception;

    String adminAction(AdminActionDTO adminActionDTO) throws Exception;

}
