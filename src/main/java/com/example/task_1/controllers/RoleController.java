package com.example.task_1.controllers;

import com.example.task_1.dto.RoleDTO;
import com.example.task_1.dto.UpdateRoleDTO;
import com.example.task_1.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> create(@Valid @RequestBody RoleDTO roleDTO){
        roleService.create(roleDTO);
        return new ResponseEntity<>(roleDTO, HttpStatus.CREATED);
    }

    @GetMapping(value ="/roles")
    public ResponseEntity<List<RoleDTO>> read() {
        final List<RoleDTO> roleDTOS = roleService.readAll();

        return roleDTOS != null && !roleDTOS.isEmpty()
                ? new ResponseEntity<>(roleDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value ="/roles/{id}")
    public ResponseEntity<RoleDTO> read(@PathVariable(name = "id") UUID id){
        final RoleDTO roleDTO = roleService.read(id);

        return roleDTO != null
                ? new ResponseEntity<>(roleDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value="/roles/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") UUID id,
                                    @Valid @RequestBody UpdateRoleDTO updDTO){
        RoleDTO roleDTO = roleService.update(id, updDTO);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") UUID id){
        final boolean deleted = roleService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
