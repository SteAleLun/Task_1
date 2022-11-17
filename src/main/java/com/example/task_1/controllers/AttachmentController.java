package com.example.task_1.controllers;

import com.example.task_1.dto.attachment.CreateAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.GetAttachmentMetadataDTO;
import com.example.task_1.services.attachment.AttachmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public class AttachmentController {

    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService){
        this.attachmentService = attachmentService;
    }

    // Добавление метаданных вложения
    //////////////// ERROR "attachments" воспринимается как Enum из UserController'а //////////////////
    @PostMapping(value = "/users/{id}/attachments")
    public ResponseEntity<GetAttachmentMetadataDTO> create(@PathVariable(name="id") UUID id,
                                                           @RequestBody CreateAttachmentMetadataDTO createAttachmentMetadataDTO){
        GetAttachmentMetadataDTO getAttachmentMetadataDTO = attachmentService.create(createAttachmentMetadataDTO, id);

        return new ResponseEntity<>(getAttachmentMetadataDTO, HttpStatus.CREATED);
    }


}
