package com.example.task_1.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

public class AttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @NotBlank
    private String title;

    // создать кастомную валидацию для символов
    @NotNull
    @NotBlank
    private String filename;

    private String description;

    private UUID versionOf;

    private String filePath;

    private boolean uploaded;

    private UUID cardId;

    @CreationTimestamp
    private Timestamp createdAt;

    // возможно неправильная реализация
    @UpdateTimestamp
    private Timestamp deletedAt;
}
