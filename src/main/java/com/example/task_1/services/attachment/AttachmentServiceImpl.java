package com.example.task_1.services.attachment;

import com.example.task_1.dto.attachment.CreateAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.GetAttachmentMetadataDTO;
import com.example.task_1.entities.AttachmentEntity;
import com.example.task_1.entities.UserEntity;
import com.example.task_1.repositories.AttachmentRepository;
import com.example.task_1.repositories.UserRepository;
import com.example.task_1.services.utils.MappingUtils;
import org.springframework.stereotype.Service;

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
    public GetAttachmentMetadataDTO create(CreateAttachmentMetadataDTO createDTO, UUID id){

        AttachmentEntity attachment = new AttachmentEntity();

        attachment.setTitle(createDTO.getTitle());
        attachment.setFilename(createDTO.getFilename());
        attachment.setDescription(createDTO.getDescription());
        attachment.setVersionOf(createDTO.getVersionOf());
        attachment.setUploaded(false);
        // Как мне обратиться к сущности пользователя без создания "лишнего"
        // не предусмотренного заданием поля cardId в createDTO?
        // Как определить его id?
        attachment.setUserEntity(userRepository.findById(id).orElse(new UserEntity()));

        return mappingUtils.mapToGetAttachmentMetadataDTO(attachmentRepository.save(attachment));
    }

}
