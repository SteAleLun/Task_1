package com.example.task_1.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;


@Entity
@Table(name="attachments")
public class AttachmentEntity {

    @Id
    @Column(name ="attachment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name ="title")
    @NotNull
    @NotBlank
    private String title;

    // создать кастомную валидацию для символов
    @Column(name ="file_name")
    @NotNull
    @NotBlank
    private String filename;

    @Column(name ="description")
    private String description;

    @OneToOne
    @JoinColumn(name="version_of")
    private AttachmentEntity attachmentEntity;

    @Column(name ="file_path")
    private String filePath;

    @Column(name ="uploaded")
    private boolean uploaded;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private UserEntity userEntity;

    @Column(name ="created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name ="deleted_at")
    private Timestamp deletedAt;


    // Constructors
    public AttachmentEntity(){
    }


    // Get/Set
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public AttachmentEntity getAttachmentEntity() {
        return attachmentEntity;
    }

    public void setAttachmentEntity(AttachmentEntity attachmentEntity) {
        this.attachmentEntity = attachmentEntity;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
