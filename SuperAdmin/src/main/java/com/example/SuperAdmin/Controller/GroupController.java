package com.example.SuperAdmin.Controller;



import com.example.SuperAdmin.DTO.AdminActionDTO;
import com.example.SuperAdmin.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SuperAdmin.DTO.GroupDTO;

import java.util.List;

@RequestMapping("/user/group")
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;


    @GetMapping("/findByMembers")
    public ResponseEntity<?> findByMembers(@RequestParam("groupId") String groupId) throws Exception{
        List<String> list = groupService.findByMembers(groupId);
        if(list!=null) {
            return new ResponseEntity<>(list,HttpStatusCode.valueOf(200));
        }
        else {
            return new ResponseEntity<>("No Data Found",HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/createGroup")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) throws Exception {
        return new ResponseEntity<>(groupService.createGroup(groupDTO),HttpStatusCode.valueOf(200));
    }

    @PostMapping("/adminAction")
    public ResponseEntity<String> adminAction(@RequestBody AdminActionDTO adminActionDTO) throws Exception {
        String res = groupService.adminAction(adminActionDTO);
        if(res=="Group Id not found") {
            return new ResponseEntity<String>(res, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<String>(res, HttpStatusCode.valueOf(200));
    }


}
