package com.example.task_1.dto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UserSetPasswordDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Поле 'oldPassword' является обязательным для заполнения!")
    @NotEmpty(message = "Поле 'oldPassword' не должно быть пустой строкой!")
    private String oldPassword;

    @NotNull(message = "Поле 'newPassword' является обязательным для заполнения!")
    @NotEmpty(message = "Поле 'newPassword' не должно быть пустой строкой!")
    private String newPassword;

    @NotNull(message = "Поле 'confirmNewPassword' является обязательным для заполнения!")
    @NotEmpty(message = "Поле 'confirmNewPassword' не должно быть пустой строкой!")
    private String confirmNewPassword;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
