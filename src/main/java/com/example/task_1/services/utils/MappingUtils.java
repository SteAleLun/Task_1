package com.example.task_1.services.utils;

import com.example.task_1.dto.UserDTO;
import com.example.task_1.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    //конвертация из entity в dto
    public UserDTO mapToUserDto(UserEntity entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFamilyName(entity.getFamilyName());
        dto.setName(entity.getName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setRole(entity.getRole());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    //конвертация из dto в entity
    public UserEntity mapToUserEntity(UserDTO dto){
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setFamilyName(dto.getFamilyName());
        entity.setName(dto.getName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setRole(dto.getRole());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

}
