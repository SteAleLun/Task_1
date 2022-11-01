package com.example.task_1.services;


import com.example.task_1.dto.UpdateUserDTO;
import com.example.task_1.dto.UserDTO;
import com.example.task_1.dto.UserSetPasswordDTO;
import com.example.task_1.dto.UserSetRoleDTO;
import com.example.task_1.entities.Status;
import com.example.task_1.entities.UserEntity;
import com.example.task_1.repositories.UserRepository;
import com.example.task_1.services.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
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
    public void create(UserDTO userDTO) {
        userRepository.save(mappingUtils.mapToUserEntity(userDTO));
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
                        .orElse(new UserEntity()) //// для чего этот метод здесь нужен?
        );
    }

    @Override
    public UserDTO update(UUID id, UpdateUserDTO updDTO) {
        if (userRepository.existsById(id)){
            UserDTO userDTO = mappingUtils.mapToUserDto(userRepository.
                    findById(id).orElse(new UserEntity()));

            userDTO.setEmail(updDTO.getEmail());
            userDTO.setFamilyName(updDTO.getFamilyName());
            userDTO.setName(updDTO.getName());
            userDTO.setMiddleName(updDTO.getMiddleName());

            userRepository.save(mappingUtils.mapToUserEntity(userDTO));
            return userDTO;
        }
        return null;
    }

    /// добавить обработку ошибок и хеширование
    @Override
    public UserDTO updatePassword(UUID id, UserSetPasswordDTO uspDTO){
        if(userRepository.existsById(id)){
            UserDTO userDTO = mappingUtils.mapToUserDto(userRepository.
                    findById(id).orElse(new UserEntity()));
            if(Objects.equals(userDTO.getPassword(), uspDTO.getOldPassword())){
                if(Objects.equals(uspDTO.getNewPassword(), uspDTO.getConfirmNewPassword())) {

                    userDTO.setPassword(uspDTO.getNewPassword());

                    userRepository.save(mappingUtils.mapToUserEntity(userDTO));
                    return userDTO;
                }
            }
        }
        return null;
    }

    @Override
    public UserDTO updateRole(UUID id, UserSetRoleDTO usrDTO){
        if(userRepository.existsById(id)){
            UserDTO userDTO = mappingUtils.mapToUserDto(userRepository.
                    findById(id).orElse(new UserEntity()));

            userDTO.setRole(usrDTO.getRole());

            userRepository.save(mappingUtils.mapToUserEntity(userDTO));
            return userDTO;
        }
        return null;
    }

    @Override
    public UserDTO setState (UUID id, Status state){
        if(userRepository.existsById(id)){
            UserDTO userDTO = mappingUtils.mapToUserDto(userRepository.
                    findById(id).orElse(new UserEntity()));

            userDTO.setStatus(state);

            userRepository.save(mappingUtils.mapToUserEntity(userDTO));
            return userDTO;
        }
        return null;
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
