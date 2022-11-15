package com.example.task_1.dto.attachment;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CreateAttachmentMetadataDTO {

    @NotNull(message = "Поле 'title' является обязательным для заполнения!")
    @NotBlank(message = "Поле 'title' не должно быть пустой строкой!")
    private String title;

    @NotNull(message = "Поле 'filename' является обязательным для заполнения!")
    @NotBlank(message = "Поле 'filename' не должно быть пустой строкой!")
    private String filename;

    private String description;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID versionOf;

    @NotNull(message = "Поле 'filename' является обязательным для заполнения!")
    private UUID cardId;


    // Constructors
    public CreateAttachmentMetadataDTO() {
    }


    // Get/Set
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getVersionOf() {
        return versionOf;
    }

    public void setVersionOf(UUID versionOf) {
        this.versionOf = versionOf;
    }

    public UUID getCardId() {
        return cardId;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }
}


