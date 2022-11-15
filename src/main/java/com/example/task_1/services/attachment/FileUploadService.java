package com.example.task_1.services.attachment;

import com.example.task_1.entities.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileUploadService {

    public void uploadToLocal(MultipartFile file);
    public UploadedFile uploadToDb(MultipartFile file);

    public UploadedFile downloadFile(UUID fileId);
}
