package com.example.task_1.controllers;


import com.example.task_1.dto.UserDTO;
import com.example.task_1.dto.UserSetPasswordDTO;
import com.example.task_1.entities.RoleEntity;
import com.example.task_1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Создание пользователя
    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO){
        userService.create(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    public ResponseEntity<?> update(@PathVariable(name="id") UUID id, @RequestBody UserDTO userDTO){
        final boolean updated = userService.update(id, userDTO);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
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
