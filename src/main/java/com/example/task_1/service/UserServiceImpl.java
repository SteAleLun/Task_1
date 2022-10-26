package com.example.task_1.service;

import com.example.task_1.Exceptions.UserAlreadyExistException;
import com.example.task_1.model.User;
import com.example.task_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // создание нового пользователя
    @Override
    public User registerNewUserAccount(User user) throws UserAlreadyExistException {
        if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + user.getEmail());
        }

        User new_user = new User();

        new_user.setName(user.getName());
        new_user.setFamilyName(user.getFamilyName());
        new_user.setMiddleName(user.getMiddleName());

        new_user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        new_user.setStatuses(user.getStatuses());
        new_user.setEmail(user.getEmail());
        new_user.setRoles(user.getRoles());

        return userRepository.save(new_user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;

    }

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
    public boolean updatePassword(UUID id, User user, String oldPassword, String password){
        if(userRepository.existsById(id)){
            String verification = user.getPassword();
            if(verification.equals(oldPassword)) {
                user.setPassword(password);
                return true;
            }
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
