package com.usercreation.UserCreation.Repo;

import ch.qos.logback.core.model.INamedModel;
import com.usercreation.UserCreation.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
