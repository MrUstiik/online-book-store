package com.example.service;

import com.example.entity.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getById(int id) {
        return userRepository.findOne(id);
    }

    public List<User> getByTelephone(String telephone) {
        return userRepository.findByTelephone(telephone);
    }

    public List<User> getByName(String firstName, String lastName) {
        return userRepository.findByLastNameAndFirstName(lastName, firstName);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByUsername(login);
    }

    @Override
    public boolean authorization(String login, String password) {
        return userRepository.findByUsernameAndPassword(login, password) != null? true : false;
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>((roleRepository.findByName("ROLE_USER"))));
        userRepository.save(user);
    }
}
