package com.example.task_1.services;

import com.example.task_1.dto.UpdateUserDTO;
import com.example.task_1.dto.UserDTO;
import com.example.task_1.dto.UserSetPasswordDTO;
import com.example.task_1.dto.UserSetRoleDTO;
import com.example.task_1.entities.Status;
import com.example.task_1.entities.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void create(UserDTO userDTO);

    List<UserDTO> readAll();

    UserDTO read(UUID id);

    UserDTO update(UUID id, UpdateUserDTO updDTO);

    UserDTO updatePassword(UUID id, UserSetPasswordDTO uspDTO);

    UserDTO updateRole(UUID id, UserSetRoleDTO usrDTO);

    UserDTO setState(UUID id, Status state);

    boolean delete(UUID id);

}
