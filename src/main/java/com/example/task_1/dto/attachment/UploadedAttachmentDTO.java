package com.example.task_1.dto.attachment;

import javax.persistence.Id;
import java.util.UUID;

public class UploadedAttachmentDTO {

    @Id
    private UUID id;

    private String fileName;

    private String fileType;

    private byte[] fileData;


    // Get/Set
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
