package com.example.task_1.services;


import com.example.task_1.dto.UserDTO;
import com.example.task_1.entities.UserEntity;
import com.example.task_1.repositories.UserRepository;
import com.example.task_1.services.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final MappingUtils mappingUtils;

    public UserServiceImpl(UserRepository userRepository, MappingUtils mappingUtils) {
        this.userRepository = userRepository;
        this.mappingUtils = mappingUtils;
    }



    @Override
    public void create(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public List<UserDTO> readAll() {
        return userRepository.findAll().stream()
                .map(mappingUtils::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO read(UUID id) {
        return mappingUtils.mapToUserDto(
                userRepository.findById(id)
                        .orElse(new UserEntity())
        );
    }

    @Override
    public boolean update(UUID id, UserEntity userEntity) {
        if (userRepository.existsById(id)){
            userEntity.setId(id);
            userRepository.save(userEntity);
        }
        return false;
    }

    @Override
    public boolean updatePassword(UUID id, UserEntity userEntity, String oldPassword, String password){
        if(userRepository.existsById(id)){
            String verification = userEntity.getPassword();
            if(verification.equals(oldPassword)) {
                userEntity.setPassword(password);
                return true;
            }
        }
        return false;
    }

    /////// новый метод изменения пароля
    public boolean updPassword(UUID id, UserEntity userEntity, String newPassword){
        if(userRepository.existsById(id)){
                userEntity.setPassword(newPassword);
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

    //возвращает true или false при поиске в таблице Users объекта который соответствует
    // типу User или принадлежит к типу объекта который наследуется от User
    public boolean exist(Example<? extends UserEntity> example){
        return userRepository.exists(example);
    }

}
