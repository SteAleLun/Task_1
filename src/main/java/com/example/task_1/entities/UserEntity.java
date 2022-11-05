package com.example.task_1.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name ="users")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name ="email")
    @NotNull
    @NotBlank
    private String email;

    @Column(name ="family_name")
    @NotNull
    @NotBlank
    private String familyName;

    @Column(name ="user_name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name ="middle_name")
    @NotBlank
    private String middleName;

    // Роль пользователя
    @ManyToOne
    @JoinColumn(name = "user_role")
    private RoleEntity roleEntity;

    @Column(name ="user_password")
    @NotNull
    @NotBlank
    private String password;

    @Column(name ="status", length = 32, columnDefinition = "varchar(32) default 'ACTIVE'")
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    // Дата и время создания пользователя
    @Column(name ="created_at")
    @CreationTimestamp
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private Timestamp createdAt;


    // Constructors
    public UserEntity(){
    }


    // Get/Set
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

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
