package com.example.task_1.controllers;

import com.example.task_1.dto.attachment.CreateAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.GetAttachmentMetadataDTO;
import com.example.task_1.dto.attachment.UploadedAttachmentDTO;
import com.example.task_1.dto.user.*;
import com.example.task_1.entities.Status;
import com.example.task_1.exception.*;
import com.example.task_1.repositories.AttachmentRepository;
import com.example.task_1.services.attachment.AttachmentService;
import com.example.task_1.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;
    private final AttachmentService attachmentService;
    private final AttachmentRepository attachmentRepository;

    @Autowired
    public UserController(UserService userService,
                          AttachmentService attachmentService,
                          AttachmentRepository attachmentRepository) {
        this.userService = userService;
        this.attachmentService = attachmentService;
        this.attachmentRepository = attachmentRepository;
    }

    // Создание пользователя
    @PostMapping(value = "/users")
    public ResponseEntity<GetUserDTO> create(@Valid @RequestBody CreateUserDTO createUserDTO) throws UserAlreadyExistException, RoleNotFoundException {
        GetUserDTO getUserDTO = userService.create(createUserDTO);

        return new ResponseEntity<>(getUserDTO, HttpStatus.CREATED);
    }

    // Получение списка всех пользователей
    @GetMapping(value ="/users")
    public ResponseEntity<List<GetUserDTO>> read() {
        final List<GetUserDTO> getUserDTOS = userService.readAll();

        return getUserDTOS != null && !getUserDTOS.isEmpty()
                ? new ResponseEntity<>(getUserDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Получение пользователя по id
    @GetMapping(value ="/users/{id}")
    public ResponseEntity<GetUserDTO> read(@PathVariable(name="id") UUID id) throws UserNotFoundException {
        final GetUserDTO getUserDTO = userService.read(id);

        return getUserDTO != null
                ? new ResponseEntity<>(getUserDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Изменить пользователя
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") UUID id,
                                    @Valid @RequestBody UpdateUserDTO updDTO) throws UserNotFoundException, UserAlreadyExistException {
        GetUserDTO getUserDTOS = userService.update(id, updDTO);
        return new ResponseEntity<>(getUserDTOS, HttpStatus.OK);
    }

    // Изменение пароля пользователя
    @PostMapping(value = "/users/{id}/set-password")
    public ResponseEntity<?> updatePassword(@PathVariable(name="id") UUID id,
                                            @Valid @RequestBody UserSetPasswordDTO uspDTO) throws UserNotFoundException, InvalidPasswordException {
        GetUserDTO getUserDTOS = userService.updatePassword(id, uspDTO);
        return new ResponseEntity<>(getUserDTOS, HttpStatus.OK);
    }

    // Изменение роли пользователя
    @PostMapping(value = "/users/{id}/set-role")
    public ResponseEntity<?> updateRole(@PathVariable(name="id") UUID id,
                                        @Valid @RequestBody UserSetRoleDTO usrDTO) throws UserNotFoundException, RoleNotFoundException {
        GetUserDTO getUserDTOS = userService.updateRole(id, usrDTO);
        return new ResponseEntity<>(getUserDTOS, HttpStatus.OK);
    }

    // изменение статуса пользователя
    @PostMapping(value = "/users/{id}/{state}")
    public ResponseEntity<?> setState(@PathVariable(name="id") UUID id,
                                      @Valid @PathVariable(name="state") Status state) throws UserNotFoundException { //
        GetUserDTO getUserDTOS = userService.setState(id, state);
        return new ResponseEntity<>(getUserDTOS, HttpStatus.OK);
    }

    // Удалить пользователя
    @DeleteMapping(value = "users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") UUID id) throws UserNotFoundException {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    // Добавление метаданных вложения
    @PostMapping(value = "/users/{id}/attachments")
    public ResponseEntity<GetAttachmentMetadataDTO> createMetadata(@PathVariable(name="id") UUID id,
                                                           @RequestBody CreateAttachmentMetadataDTO createAttachmentMetadataDTO) throws UserNotFoundException, FileNotFoundException {
        GetAttachmentMetadataDTO getAttachmentMetadataDTO = attachmentService.create(id, createAttachmentMetadataDTO);

        return new ResponseEntity<>(getAttachmentMetadataDTO, HttpStatus.CREATED);
    }

    // Добавление файла вложения (локальное сохранение)
    @PostMapping(value ="/files/{id}")
    public ResponseEntity<GetAttachmentMetadataDTO> createFile(@PathVariable(name="id") UUID id,
                                                               @RequestParam("file")MultipartFile file) throws IOException, FileAlreadyUploadedException {

        GetAttachmentMetadataDTO getAttachmentMetadataDTO = attachmentService.uploadToLocal(id, file);

        return new ResponseEntity<>(getAttachmentMetadataDTO, HttpStatus.OK);
    }

    // Удаление файла
    @DeleteMapping(value = "/files/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable(name = "id") UUID id) throws FileNotFoundException {
        final boolean deleted = attachmentService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // Получить список файлов для пользователя
    @GetMapping(value = "/users/{id}/attachments")
    public ResponseEntity<List<GetAttachmentMetadataDTO>> getFiles(@PathVariable(name = "id") UUID id) throws UserNotFoundException {
        final List<GetAttachmentMetadataDTO> dtos = attachmentService.getFiles(id);

        return dtos != null && !dtos.isEmpty()
                ? new ResponseEntity<>(dtos, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Получить информацию о файле
    @GetMapping(value = "/files/{id}")
    public ResponseEntity<GetAttachmentMetadataDTO> getMetadata(@PathVariable(name = "id") UUID id) throws FileNotFoundException {
        final GetAttachmentMetadataDTO dto = attachmentService.getMetadata(id);

        return dto != null
                ? new ResponseEntity<>(dto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TESTING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Выгрузить файл
    @GetMapping(value = "/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable(name = "id") UUID id) throws IOException {

        UploadedAttachmentDTO file = attachmentService.downloadFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= "
                            + file.getFileName())
                                .body(new ByteArrayResource(file.getFileData()));
    }

}
