package com.example.task_1.services;


import com.example.task_1.dto.RoleDTO;
import com.example.task_1.dto.UpdateRoleDTO;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    void create(RoleDTO roleDTO);

    List<RoleDTO> readAll();

    RoleDTO read(UUID id);

    RoleDTO update(UUID id, UpdateRoleDTO updDTO);

    boolean delete(UUID id);
}
