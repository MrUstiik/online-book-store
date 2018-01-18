package com.example.service.interfaces;



import com.example.entity.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAll();
    User addUser(User user);
    User editUser(User user);
    void deleteUser(User user);
    User getById(int id);
    List<User> getByTelephone(String telephone);
    List<User> getByName(String firstName, String lastName);
    User getByLogin(String login);
    boolean authorization(String login, String password);
    void save(User user);
}
