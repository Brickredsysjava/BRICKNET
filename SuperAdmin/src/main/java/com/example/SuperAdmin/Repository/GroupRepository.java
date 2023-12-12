package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.Entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group,String> {
    Group findByGroupName(String groupName);

    List<Group> findByAdmin(String admin);
}
