package com.example.task_1.controllers;


import com.example.task_1.dto.user.*;
import com.example.task_1.entities.Status;
import com.example.task_1.exception.InvalidPasswordException;
import com.example.task_1.exception.UserAlreadyExistException;
import com.example.task_1.exception.UserNotFoundException;
import com.example.task_1.services.UserService;
import com.example.task_1.services.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;
    private final MappingUtils mappingUtils;

    @Autowired
    public UserController(UserService userService, MappingUtils mappingUtils) {
        this.userService = userService;
        this.mappingUtils = mappingUtils;
    }

    // Создание пользователя
    @PostMapping(value = "/users")
    public ResponseEntity<GetUserDTO> create(@Valid @RequestBody CreateUserDTO createUserDTO) throws UserAlreadyExistException {
        GetUserDTO getUserDTO = mappingUtils.mapToGetUserDTO(
                mappingUtils.mapToUserEntity(createUserDTO)
        );

        userService.create(createUserDTO);
        return new ResponseEntity<>(getUserDTO, HttpStatus.CREATED);
    }

    // Получение списка всех пользователей
    @GetMapping(value ="/users")
    public ResponseEntity<List<GetUserDTO>> read() {
        final List<GetUserDTO> getUserDTOS = userService.readAll();

        return getUserDTOS != null && !getUserDTOS.isEmpty()
                ? new ResponseEntity<>(getUserDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Получение пользователя по id
    @GetMapping(value ="/users/{id}")
    public ResponseEntity<GetUserDTO> read(@PathVariable(name="id") UUID id) throws UserNotFoundException {
        final GetUserDTO getUserDTOS = userService.read(id);

        return getUserDTOS != null
                ? new ResponseEntity<>(getUserDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Изменить пользователя
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") UUID id,
                                    @Valid @RequestBody UpdateUserDTO updDTO) throws UserNotFoundException {
        GetUserDTO getUserDTOS = userService.update(id, updDTO);
        return new ResponseEntity<>(getUserDTOS, HttpStatus.OK);
    }

    // Изменение пароля пользователя
    @PostMapping(value = "/users/{id}/set-password")
    public ResponseEntity<?> updatePassword(@PathVariable(name="id") UUID id,
                                            @Valid @RequestBody UserSetPasswordDTO uspDTO) throws UserNotFoundException, InvalidPasswordException {
        GetUserDTO getUserDTOS = userService.updatePassword(id, uspDTO);
        return new ResponseEntity<>(getUserDTOS, HttpStatus.OK);
    }

    // Изменение роли пользователя
    @PostMapping(value = "/users/{id}/set-role")
    public ResponseEntity<?> updateRole(@PathVariable(name="id") UUID id,
                                        @Valid @RequestBody UserSetRoleDTO usrDTO) throws UserNotFoundException {
        GetUserDTO getUserDTOS = userService.updateRole(id, usrDTO);
        return new ResponseEntity<>(getUserDTOS, HttpStatus.OK);
    }

    // изменение статуса пользователя
    @PostMapping(value = "/users/{id}/{state}")
    public ResponseEntity<?> setState(@PathVariable(name="id") UUID id,
                                      @Valid @PathVariable(name="state") Status state) throws UserNotFoundException {
        GetUserDTO getUserDTOS = userService.setState(id, state);
        return new ResponseEntity<>(getUserDTOS, HttpStatus.OK);
    }

    // Удалить пользователя
    @DeleteMapping(value = "users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") UUID id) throws UserNotFoundException {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
