package com.example.task_1.services;

import com.example.task_1.dto.RoleDTO;
import com.example.task_1.dto.UpdateRoleDTO;
import com.example.task_1.entities.RoleEntity;
import com.example.task_1.entities.UserEntity;
import com.example.task_1.repositories.RoleRepository;
import com.example.task_1.services.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    private final MappingUtils mappingUtils;

    public RoleServiceImpl(RoleRepository roleRepository, MappingUtils mappingUtils) {
        this.roleRepository = roleRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public void create(RoleDTO roleDTO) {
        roleRepository.save(mappingUtils.mapToRoleEntity(roleDTO));
    }

    // получить список ролей
    @Override
    public List<RoleDTO> readAll() {
        return roleRepository.findAll().stream()
                .map(mappingUtils::mapToRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO read(UUID id) {
        return mappingUtils.mapToRoleDto(
                roleRepository.findById(id)
                        .orElse(new RoleEntity())
        );
    }

    @Override
    public RoleDTO update(UUID id, UpdateRoleDTO updDTO) {
        if (roleRepository.existsById(id)) {
            RoleDTO roleDTO = mappingUtils.mapToRoleDto(roleRepository.
                    findById(id).orElse(new RoleEntity()));

            roleDTO.setName(updDTO.getName());
            roleDTO.setDescription(updDTO.getDescription());

            roleRepository.save(mappingUtils.mapToRoleEntity(roleDTO));
            return roleDTO;
        }
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
