package com.example.task_1.service;

import com.example.task_1.model.User;
import com.example.task_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
    public boolean update(User user, UUID id) {
        if (userRepository.existsById(id)){
            user.setId(id);
            userRepository.save(user);
        }
        return false;
    }


   /* public boolean changePassword(UUID id){

    }*/


    @Override
    public boolean delete(UUID id) {
        if(userRepository.existsById(id)) {
           userRepository.deleteById(id);
           return true;
        }
        return false;
    }

}
