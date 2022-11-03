package com.example.task_1.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


////////////////////////////////////////////////////////////////
///                         ID ролей:                        ///
///  "Администратор": bc7de22d-f176-4594-a250-77e580f10aca   ///
///  "Сотрудник": 16d2745a-c050-4a3d-97bb-9addd96f30eb       ///
///  "Наблюдатель": cd03925b-1ac9-41d2-955b-b9d98109df4d     ///
////////////////////////////////////////////////////////////////


public class RoleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Поле 'name' является обязательным для заполнения!")
    @NotBlank(message = "Поле 'name' не должно быть пустой строкой!")
    private String name;

    @NotBlank(message = "Поле 'description' не должно быть пустой строкой!")
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
