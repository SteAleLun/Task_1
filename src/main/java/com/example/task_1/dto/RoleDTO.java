package com.example.task_1.dto;

import com.example.task_1.entities.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

public class RoleDTO {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "description")
    private String description;

    @Transient
    @ManyToMany(mappedBy = "roleEntities")
    private Set<UserEntity> userEntities;
}
