package com.example.task_1.dto.role;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UpdateRoleDTO {

    @Id
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
