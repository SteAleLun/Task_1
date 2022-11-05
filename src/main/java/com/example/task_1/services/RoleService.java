package com.example.task_1.services;


import com.example.task_1.dto.role.RoleDTO;
import com.example.task_1.dto.role.UpdateRoleDTO;
import com.example.task_1.exception.RoleNotFoundException;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    void create(RoleDTO roleDTO);

    List<RoleDTO> readAll();

    RoleDTO read(UUID id) throws RoleNotFoundException;

    RoleDTO update(UUID id, UpdateRoleDTO updDTO) throws RoleNotFoundException;

    boolean delete(UUID id) throws RoleNotFoundException;
}
