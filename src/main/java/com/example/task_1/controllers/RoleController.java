package com.example.task_1.controllers;

import com.example.task_1.entities.RoleEntity;
import com.example.task_1.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value ="/roles")
    public ResponseEntity<?> create(@RequestBody RoleEntity roleEntity){
        roleService.create(roleEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value ="/roles")
    public ResponseEntity<List<RoleEntity>> read() {
        final List<RoleEntity> roleEntities = roleService.readAll();

        return roleEntities != null && !roleEntities.isEmpty()
                ? new ResponseEntity<>(roleEntities, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value ="/roles/{id}")
    public ResponseEntity<RoleEntity> read(@PathVariable(name = "id") UUID id){
        final RoleEntity roleEntity = roleService.read(id);

        return roleEntity != null
                ? new ResponseEntity<>(roleEntity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value="/roles/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") UUID id, @RequestBody RoleEntity roleEntity){
        final boolean updated = roleService.update(roleEntity, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") UUID id){
        final boolean deleted = roleService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
