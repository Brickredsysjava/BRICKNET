package com.microservice.login.Login.repositories;

import com.microservice.login.Login.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Repository> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByName(String name);
}
