package com.example.task_1.services.utils;

import com.example.task_1.dto.role.RoleDTO;
import com.example.task_1.dto.user.CreateUserDTO;
import com.example.task_1.dto.user.GetUserDTO;
import com.example.task_1.entities.RoleEntity;
import com.example.task_1.entities.UserEntity;
import com.example.task_1.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    private final RoleRepository roleRepository;

    public MappingUtils(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // конвертация из UserEntity в CreateUserDTO
    public CreateUserDTO mapToCreateUserDTO(UserEntity entity){
        CreateUserDTO dto = new CreateUserDTO();
        dto.setEmail(entity.getEmail());
        dto.setFamilyName(entity.getFamilyName());
        dto.setName(entity.getName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setRole(entity.getRoleEntity().getId());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    // конвертация из CreateUserDTO в UserEntity
    public UserEntity mapToUserEntity(CreateUserDTO dto){
        UserEntity entity = new UserEntity();
        entity.setEmail(dto.getEmail());
        entity.setFamilyName(dto.getFamilyName());
        entity.setName(dto.getName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setRoleEntity(roleRepository.findById(dto.getRole()).orElse(new RoleEntity()));
        entity.setPassword(dto.getPassword());
        return entity;
    }

    // конвертация из UserEntity в GetUserDTO
    public GetUserDTO mapToGetUserDTO(UserEntity entity){
        GetUserDTO dto = new GetUserDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFamilyName(entity.getFamilyName());
        dto.setName(entity.getName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setRole(entity.getRoleEntity().getId());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    // конвертация из CreateUserDTO в UserEntity
    public UserEntity mapToUserEntity(GetUserDTO dto){
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setFamilyName(dto.getFamilyName());
        entity.setName(dto.getName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setRoleEntity(roleRepository.findById(dto.getRole()).orElse(new RoleEntity()));
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }

    // конвертация из entity в dto для Role
    public RoleDTO mapToRoleDto(RoleEntity entity){
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    // конвертация из dto в entity для Role
    public RoleEntity mapToRoleEntity(RoleDTO dto){
        RoleEntity entity = new RoleEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
