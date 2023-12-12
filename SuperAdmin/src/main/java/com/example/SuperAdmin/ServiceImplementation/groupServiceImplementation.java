package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.DTO.AdminActionDTO;
import com.example.SuperAdmin.DTO.GroupDTO;
import com.example.SuperAdmin.Entity.Group;
import com.example.SuperAdmin.Repository.GroupRepository;
import com.example.SuperAdmin.Service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class groupServiceImplementation implements GroupService {

    @Autowired
    private GroupRepository groupRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<String> findByMembers(String groupId) throws Exception{

        try{
            return groupRepository.findById(groupId).get().getMembers();
        }
        catch(Exception e) {
             e.getMessage();
        }
        return null;
    }


    @Override
    public GroupDTO createGroup(GroupDTO groupDTO) throws Exception{
        Group group = groupRepository.save(modelMapper.map(groupDTO, Group.class));
        groupDTO.setGroupId(group.getGroupId());
        return groupDTO;
    }

    @Override
    public String adminAction(AdminActionDTO adminActionDTO) throws Exception{
        Group group = groupRepository.findById(adminActionDTO.getGroupId()).get();

        String adminEmployeeCode = group.getAdmin();
        if(! adminActionDTO.getAdminEmployeeCode().equals(adminEmployeeCode)) {
            return "No Action Allowed";
        }

        if((adminActionDTO.getAction().equals(true)) &&
                (! group.getMembers().stream().anyMatch(i->i.equals(adminActionDTO.getMemberEmployeeCode())))) {

                group.getMembers().add(adminActionDTO.getMemberEmployeeCode());
                groupRepository.save(group);

                return "Member Successfully added";
        }

        else if(group.getMembers().stream().anyMatch(i->i.equals(adminActionDTO.getMemberEmployeeCode()))) {
                group.getMembers().remove(adminActionDTO.getMemberEmployeeCode());
                groupRepository.save(group);

            return "Member Successfully removed";
        }
        return "No Action Allowed";
    }
}
