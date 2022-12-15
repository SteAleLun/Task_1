package com.example.task_1.services.attachment;

import com.example.task_1.dto.attachment.CreateAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.GetAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.UploadedAttachmentDTO;
import com.example.task_1.exception.FileAlreadyUploadedException;
import com.example.task_1.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface AttachmentService {

    // Добавление метаданных вложения
    GetAttachmentMetadataDTO create(UUID id, CreateAttachmentMetadataDTO createDTO) throws UserNotFoundException, FileNotFoundException;

    // Добавление файла вложения
    GetAttachmentMetadataDTO uploadToLocal(UUID id, MultipartFile file) throws FileAlreadyUploadedException, IOException;

    // Удаление файла
    boolean delete(UUID id) throws FileNotFoundException;

    // Получить список файлов для пользователя
    List<GetAttachmentMetadataDTO> getFiles(UUID id) throws UserNotFoundException;

    // Получить информацию о файле
    GetAttachmentMetadataDTO getMetadata(UUID id) throws FileNotFoundException;

    // Выгрузить файл
    UploadedAttachmentDTO downloadFile(UUID id) throws IOException;
}

