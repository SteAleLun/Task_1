package com.example.task_1.services.user;


import com.example.task_1.dto.user.*;
import com.example.task_1.entities.Status;
import com.example.task_1.exception.InvalidPasswordException;
import com.example.task_1.exception.RoleNotFoundException;
import com.example.task_1.exception.UserAlreadyExistException;
import com.example.task_1.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    GetUserDTO create(CreateUserDTO createUserDTO) throws UserAlreadyExistException, RoleNotFoundException;

    List<GetUserDTO> readAll();

    GetUserDTO read(UUID id) throws UserNotFoundException;

    GetUserDTO update(UUID id, UpdateUserDTO updDTO) throws UserNotFoundException, UserAlreadyExistException;

    GetUserDTO updatePassword(UUID id, UserSetPasswordDTO uspDTO) throws UserNotFoundException, InvalidPasswordException;

    GetUserDTO updateRole(UUID id, UserSetRoleDTO usrDTO) throws UserNotFoundException, RoleNotFoundException;

    GetUserDTO setState(UUID id, Status state) throws UserNotFoundException;

    boolean delete(UUID id) throws UserNotFoundException;

}
