package com.example.task_1.services.attachment;

import com.example.task_1.dto.attachment.CreateAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.GetAttachmentMetadataDTO;
import com.example.task_1.entities.AttachmentEntity;
import com.example.task_1.entities.UserEntity;
import com.example.task_1.exception.UserNotFoundException;
import com.example.task_1.repositories.AttachmentRepository;
import com.example.task_1.repositories.UserRepository;
import com.example.task_1.services.utils.MappingUtils;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;
    private final MappingUtils mappingUtils;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository,
                                 UserRepository userRepository,
                                 MappingUtils mappingUtils){
        this.attachmentRepository = attachmentRepository;
        this.userRepository = userRepository;
        this.mappingUtils = mappingUtils;
    }

    // Добавление метаданных вложения
    @Override
    public GetAttachmentMetadataDTO create(CreateAttachmentMetadataDTO createDTO, UUID id) throws UserNotFoundException, FileNotFoundException {

        AttachmentEntity attachment = new AttachmentEntity();

        attachment.setTitle(createDTO.getTitle());
        attachment.setFilename(createDTO.getFilename());
        attachment.setDescription(createDTO.getDescription());
        attachment.setAttachmentEntity(attachmentRepository.findById(createDTO.getVersionOf())
                .orElseThrow(
                        () -> new FileNotFoundException("Файл с таким ID не найден!")
                ));
        attachment.setUploaded(false);
        attachment.setUserEntity(userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Пользователь с таким ID не найден!")
        ));

        return mappingUtils.mapToGetAttachmentMetadataDTO(attachmentRepository.save(attachment));
    }

}
