package com.example.task_1.service;

import com.example.task_1.model.Role;
import com.example.task_1.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void create(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> readAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role read(UUID id) {
        return roleRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Role role, UUID id) {
        if (roleRepository.existsById(id)) {
            role.setId(id);
            roleRepository.save(role);
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
