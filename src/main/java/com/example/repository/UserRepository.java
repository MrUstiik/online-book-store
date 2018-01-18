package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByTelephone(String telephone);
    List<User> findByLastNameAndFirstName(String lastName, String firstName);
    User findByUsername(String username);
    User findByUsernameAndPassword(String login, String password);
}
