package com.example.task_1.services;

import com.example.task_1.entities.RoleEntity;
import com.example.task_1.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void create(RoleEntity roleEntity) {
        roleRepository.save(roleEntity);
    }

    @Override
    public List<RoleEntity> readAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity read(UUID id) {
        return roleRepository.getReferenceById(id);
    }

    @Override
    public boolean update(RoleEntity roleEntity, UUID id) {
        if (roleRepository.existsById(id)) {
            roleEntity.setId(id);
            roleRepository.save(roleEntity);
            return true;
        }
        return false;
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
