package com.example.task_1.service;

import com.example.task_1.model.User;
import com.example.task_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public User read(UUID id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public boolean update(UUID id, User user) {
        if (userRepository.existsById(id)){
            user.setId(id);
            userRepository.save(user);
        }
        return false;
    }

    @Override
    public boolean updatePassword(UUID id){
        if(userRepository.existsById(id)){

            return true;
        }
        return false;
    }

    @Override
    public boolean updateRole(UUID id){
        if(userRepository.existsById(id)){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        if(userRepository.existsById(id)) {
           userRepository.deleteById(id);
           return true;
        }
        return false;
    }

}
