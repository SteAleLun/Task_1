package com.example.task_1.services.attachment;

import com.example.task_1.dto.attachment.CreateAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.GetAttachmentMetadataDTO;

import java.util.UUID;

public interface AttachmentService {

    // Добавление метаданных вложения
    GetAttachmentMetadataDTO create(CreateAttachmentMetadataDTO createDTO, UUID id);
}
