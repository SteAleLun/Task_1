package com.example.task_1.controllers;

import com.example.task_1.dto.attachment.FileUploadResponse;
import com.example.task_1.entities.UploadedFile;
import com.example.task_1.services.attachment.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService){
        this.fileUploadService = fileUploadService;
    }

    // Локальное сохранение
    @PostMapping("/upload/local")
    public void uploadLocal(@RequestParam("file")MultipartFile multipartFile){
        fileUploadService.uploadToLocal(multipartFile);
    }

    // Сохранение в БД
    @PostMapping("/upload/db")
    public void uploadDb(@RequestParam("file")MultipartFile multipartFile){
        fileUploadService.uploadToDb(multipartFile);
    }

    // Сохранить в БД и получить информацию о файле и ссылку на скачивание
    @PostMapping("/another-upload/db")
    public FileUploadResponse anotherUploadDb(@RequestParam("file")MultipartFile multipartFile){
        UploadedFile uploadedFile = fileUploadService.uploadToDb(multipartFile);
        FileUploadResponse response = new FileUploadResponse();

        if(uploadedFile != null){
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(String.valueOf(uploadedFile.getId())).toUriString();
            response.setDownloadUri(downloadUri);
            response.setId(uploadedFile.getId());
            response.setFileType(uploadedFile.getFileType());
            response.setUploadStatus(true);
            response.setMessage("File uploaded successfully");

            return response;
        }
        response.setMessage("Something went wrong!");
        return response;
    }

    // Получение информации о файле
    @GetMapping("get-file-info/{id}")
    public UploadedFile getFileInfo(@PathVariable(name= "id") UUID id){
        return fileUploadService.downloadFile(id);
    }

    // Скачать файл
    @GetMapping("download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable(name= "id") UUID id){
        UploadedFile uploadedFile = fileUploadService.downloadFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploadedFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " +uploadedFile.getFileName())
                .body(new ByteArrayResource(uploadedFile.getFileData()));
    }
}
