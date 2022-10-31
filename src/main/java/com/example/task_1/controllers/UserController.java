package com.example.task_1.controllers;


import com.example.task_1.dto.UpdateUserDTO;
import com.example.task_1.dto.UserDTO;
import com.example.task_1.dto.UserSetPasswordDTO;
import com.example.task_1.entities.RoleEntity;
import com.example.task_1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Создание пользователя
    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO){
        userService.create(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    // Получение списка всех пользователей
    @GetMapping(value ="/users")
    public ResponseEntity<List<UserDTO>> read(){
        final List<UserDTO> userDTOS = userService.readAll();

        return userDTOS != null && !userDTOS.isEmpty()
                ? new ResponseEntity<>(userDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Получение пользователя по id
    @GetMapping(value ="/users/{id}")
    public ResponseEntity<UserDTO> read(@PathVariable(name="id") UUID id){
        final UserDTO userDTO = userService.read(id);

        return userDTO != null
                ? new ResponseEntity<>(userDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Изменить пользователя
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") UUID id, @RequestBody UpdateUserDTO updDTO){
        UserDTO userDTO = userService.update(id, updDTO);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    // Изменение пароля пользователя
    @PutMapping(value = "/users/{id}/set-password")
    public ResponseEntity<?> updatePassword(@PathVariable(name="id") UUID id, UserSetPasswordDTO uspDTO)
    {
        final boolean updated = userService.updatePassword(id, uspDTO);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // Изменение роли пользователя
    @PutMapping(value = "/users/{id}/set-role")
    public ResponseEntity<?> updateRole(@PathVariable(name="id") UUID id, Set<RoleEntity> roleEntity){
        final boolean updated = userService.updateRole(id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // Удалить пользователя
    @DeleteMapping(value = "users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") UUID id){
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
