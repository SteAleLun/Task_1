package com.example.task_1.services.attachment;

import com.example.task_1.entities.UploadedFile;
import com.example.task_1.repositories.FileUploadRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final String uploadFolderPath = "C:\\Users\\SteAleLun\\Desktop\\uploaded_";

    private final FileUploadRepository fileUploadRepository;

    public FileUploadServiceImpl(FileUploadRepository fileUploadRepository){
        this.fileUploadRepository = fileUploadRepository;
    }

    // Локальное сохранение
    @Override
    public void uploadToLocal(MultipartFile file) {
        try {
            byte[] data = file.getBytes();
            Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
            Files.write(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Сохранение в БД
    @Override
    public UploadedFile uploadToDb(MultipartFile file) {
        UploadedFile uploadedFile = new UploadedFile();
        try {
            uploadedFile.setFileData(file.getBytes());
            uploadedFile.setFileType(file.getContentType());
            uploadedFile.setFileName(file.getOriginalFilename());
            return fileUploadRepository.save(uploadedFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // получение информации о файле
    @Override
    public UploadedFile downloadFile(UUID fileId) {
        return fileUploadRepository.findById(fileId).orElse(new UploadedFile());
    }

}
