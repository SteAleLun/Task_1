package com.example.task_1.controller;

import com.example.task_1.model.Role;
import com.example.task_1.model.User;
import com.example.task_1.service.UserService;
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
    public ResponseEntity<?> create(@RequestBody User user){
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Получение списка всех пользователей
    @GetMapping(value ="/users")
    public ResponseEntity<List<User>> read(){
        final List<User> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Получение пользователя по id
    @GetMapping(value ="/users/{id}")
    public ResponseEntity<User> read(@PathVariable(name="id") UUID id){
        final User user = userService.read(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Обновить пользователя
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") UUID id, @RequestBody User user){
        final boolean updated = userService.update(id, user);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // Изменение пароля пользователя
    @PutMapping(value = "/users/{id}/set-password")
    public ResponseEntity<?> updatePassword(@PathVariable(name="id") UUID id,
                                            @RequestParam("oldPassword")String oldPassword,
                                            @RequestParam("password") String password)
    {
        final boolean updated = userService.updatePassword(id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // Изменение роли пользователя
    @PutMapping(value = "/users/{id}/set-role")
    public ResponseEntity<?> updateRole(@PathVariable(name="id") UUID id, Set<Role> role){
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
