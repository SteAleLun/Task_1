package com.example.task_1.services;


import com.example.task_1.dto.UpdateUserDTO;
import com.example.task_1.dto.UserDTO;
import com.example.task_1.dto.UserSetPasswordDTO;
import com.example.task_1.dto.UserSetRoleDTO;
import com.example.task_1.entities.Status;
import com.example.task_1.exception.InvalidPasswordException;
import com.example.task_1.exception.UserAlreadyExistException;
import com.example.task_1.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void create(UserDTO userDTO) throws UserAlreadyExistException;

    List<UserDTO> readAll();

    UserDTO read(UUID id) throws UserNotFoundException;

    UserDTO update(UUID id, UpdateUserDTO updDTO) throws UserNotFoundException;

    UserDTO updatePassword(UUID id, UserSetPasswordDTO uspDTO) throws UserNotFoundException, InvalidPasswordException;

    UserDTO updateRole(UUID id, UserSetRoleDTO usrDTO) throws UserNotFoundException;

    UserDTO setState(UUID id, Status state) throws UserNotFoundException;

    boolean delete(UUID id) throws UserNotFoundException;

}
