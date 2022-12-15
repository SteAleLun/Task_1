package com.example.task_1.services.attachment;

import com.example.task_1.dto.attachment.CreateAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.GetAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.UploadedAttachmentDTO;
import com.example.task_1.entities.AttachmentEntity;
import com.example.task_1.entities.UserEntity;
import com.example.task_1.exception.FileAlreadyUploadedException;
import com.example.task_1.exception.UserNotFoundException;
import com.example.task_1.repositories.AttachmentRepository;
import com.example.task_1.repositories.UserRepository;
import com.example.task_1.services.utils.MappingUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;
    private final MappingUtils mappingUtils;

    private final String uploadFolderPath = "C:\\Users\\SteAleLun\\Desktop\\uploaded_";

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository,
                                 UserRepository userRepository,
                                 MappingUtils mappingUtils) {
        this.attachmentRepository = attachmentRepository;
        this.userRepository = userRepository;
        this.mappingUtils = mappingUtils;
    }


    // Добавление метаданных вложения
    @Override
    public GetAttachmentMetadataDTO create(UUID id, CreateAttachmentMetadataDTO createDTO) throws UserNotFoundException, FileNotFoundException {

        if(!userRepository.existsById(id))
            throw new UserNotFoundException("Пользователь с ID '" + id + "' не найден");

        AttachmentEntity attachment = new AttachmentEntity();

        attachment.setTitle(createDTO.getTitle());
        attachment.setFilename(createDTO.getFilename());
        attachment.setDescription(createDTO.getDescription());

        if(createDTO.getVersionOf() != null)
        {
            if(!attachmentRepository.existsById(createDTO.getVersionOf()))
                throw new FileNotFoundException(
                        "versionOf: Файла с ID '" + createDTO.getVersionOf() + "' не существует");
            attachment.setAttachmentEntity(
                attachmentRepository.findById(createDTO.getVersionOf())
                        .orElse(new AttachmentEntity()));
        }

        attachment.setUploaded(false);
        attachment.setUserEntity(userRepository.findById(id).orElse(new UserEntity()));

        return mappingUtils.mapToGetAttachmentMetadataDTO(attachmentRepository.save(attachment));
    }


    // Добавление файла вложения
    @Override
    public GetAttachmentMetadataDTO uploadToLocal(UUID id, MultipartFile file) throws FileAlreadyUploadedException, IOException {

        if (attachmentRepository.existsById(id))
        {
            if (attachmentRepository.findById(id).orElse(new AttachmentEntity()).isUploaded())
                throw new FileAlreadyUploadedException("Файл уже загружен");
            else
            {
                AttachmentEntity attachment = attachmentRepository.findById(id).orElse(new AttachmentEntity());

                // локальное сохранение файла
                byte[] data = file.getBytes();
                Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
                Files.write(path, data);

                attachment.setFilename(file.getOriginalFilename());
                attachment.setFilePath(path.toString());
                attachment.setUploaded(true);

                return mappingUtils.mapToGetAttachmentMetadataDTO(attachmentRepository.save(attachment));
            }
        }
        throw new FileNotFoundException("Файл вложения с идентификатором '" + id + "' не найден");
    }


    // Удаление файла
    public boolean delete(UUID id) throws FileNotFoundException {
        if (attachmentRepository.existsById(id))
        {
            AttachmentEntity attachment = attachmentRepository.findById(id).orElse(new AttachmentEntity());
            if (!attachment.isUploaded())
            {
                attachmentRepository.deleteById(id);
                return true;
            }
            else
            {
                if(attachment.getDeletedAt() == null)
                {
                    attachment.setDeletedAt(new Timestamp(System.currentTimeMillis()));
                    attachmentRepository.save(attachment);
                    return true;
                }
                else
                    throw new FileNotFoundException("Файл ранее был удалён");
            }
        }
        else
            throw new FileNotFoundException("Файл вложения с идентификатором '" + id + "' не найден");
    }


    // Получить список файлов для пользователя
    public List<GetAttachmentMetadataDTO> getFiles(UUID id) throws UserNotFoundException {
        if (userRepository.existsById(id))
        {
            return attachmentRepository.findAll().stream()
                    .map(mappingUtils::mapToGetAttachmentMetadataDTO)
                    .collect(Collectors.toList());
        }
        else
            throw new UserNotFoundException("Пользователь с идентификатором '" + id + "' не найден");
    }


    // Получить информацию о файле
    public GetAttachmentMetadataDTO getMetadata(UUID id) throws FileNotFoundException {
        if (attachmentRepository.existsById(id)) {
            return mappingUtils.mapToGetAttachmentMetadataDTO(attachmentRepository.findById(id)
                    .orElse(new AttachmentEntity()));
        }
        else
            throw new FileNotFoundException("Файл вложения с идентификатором '" + id + "' не найден!");
    }


    // Выгрузить файл
    public UploadedAttachmentDTO downloadFile(UUID id) throws IOException {
        if (attachmentRepository.existsById(id)) {
            if (attachmentRepository.findById(id).orElse(new AttachmentEntity())
                    .getFilePath() != null) {
                if(attachmentRepository.findById(id).orElse(new AttachmentEntity())
                        .isUploaded()){
                    if(attachmentRepository.findById(id).orElse(new AttachmentEntity())
                            .getDeletedAt() == null){

                        AttachmentEntity attachment = attachmentRepository.findById(id)
                                .orElse(new AttachmentEntity());
                        UploadedAttachmentDTO dto = new UploadedAttachmentDTO();

                        dto.setFileName(attachment.getFilename());
                        dto.setFileType(getFileType(attachment));
                        dto.setFileData(Files.readAllBytes(Paths.get(attachment.getFilePath())));

                        return dto;
                    }
                    else throw new FileNotFoundException("Файл ранее был удалён");
                }
                else throw new FileNotFoundException("Файл не был загружен в систему");
            }
            else throw new FileNotFoundException("Отсутствует путь к файлу в системе");
        }
        else throw new FileNotFoundException("Файл вложения с идентификатором '" + id + "' не найден");
    }


    // Получить тип файла из пути в системе
    private String getFileType(AttachmentEntity attachment){
        return attachment.getFilePath()
                .substring(attachment.getFilePath().lastIndexOf(".") + 1);
    }

}



