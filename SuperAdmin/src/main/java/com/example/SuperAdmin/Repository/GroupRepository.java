package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.Entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface GroupRepository extends JpaRepository<Group,String> {
    Group findByGroupName(String groupName);

}
