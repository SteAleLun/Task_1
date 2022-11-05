package com.example.task_1.dto.user;

import com.example.task_1.entities.Status;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

public class GetUserDTO {

    @Id
    private UUID id;

    @NotNull(message = "Поле 'email' является обязательным для заполнения!")
    @NotBlank(message = "Поле 'email' не должно быть пустой строкой!")
    @Email(message = "Некорректный формат введённого email!")
    private String email;

    @NotNull(message = "Поле 'familyName' является обязательным для заполнения!")
    @NotBlank(message = "Поле 'familyName' не должно быть пустой строкой!")
    private String familyName;

    @NotNull(message = "Поле 'name' является обязательным для заполнения!")
    @NotBlank(message = "Поле 'name' не должно быть пустой строкой!")
    private String name;

    @NotBlank(message = "Поле 'middleName' не должно быть пустой строкой!")
    private String middleName;

    @NotNull(message = "Поле 'role' является обязательным для заполнения!")
    private UUID role;

    private Status status = Status.ACTIVE;

    private Timestamp createdAt;


    // Constructors
    public GetUserDTO(){
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

    public UUID getRole() {
        return role;
    }

    public void setRole(UUID role) {
        this.role = role;
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
