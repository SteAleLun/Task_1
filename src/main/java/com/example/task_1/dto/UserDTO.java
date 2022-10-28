package com.example.task_1.dto;

import com.example.task_1.entities.RoleEntity;
import com.example.task_1.entities.Status;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

public class UserDTO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name ="email")
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @Column(name ="familyName")
    @NotNull
    @NotEmpty
    private String familyName;

    @Column(name ="name")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name ="middleName")
    @NotNull
    @NotEmpty
    private String middleName;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<RoleEntity> role;

    @Column(name ="password")
    @NotNull
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name ="createdAt")
    @NotNull
    @NotEmpty
    private Timestamp createdAt;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getRole() {
        return role;
    }

    public void setRole(Set<RoleEntity> role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
