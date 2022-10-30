package com.example.task_1.dto;

import com.example.task_1.entities.Status;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @NotEmpty(message = "Поле \"email\"является обязательным для заполнения")
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String familyName;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String middleName;

    @NotNull
    private UUID role;

    @NotNull
    @NotEmpty
    private String password;


    @Column(name ="status", length = 32, columnDefinition = "varchar(32) default 'ACTIVE'")
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    @CreationTimestamp
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

    public UUID getRole() {
        return role;
    }

    public void setRole(UUID role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
