package com.example.task_1.services;


import com.example.task_1.dto.UpdateUserDTO;
import com.example.task_1.dto.UserDTO;
import com.example.task_1.dto.UserSetPasswordDTO;
import com.example.task_1.dto.UserSetRoleDTO;
import com.example.task_1.entities.Status;
import com.example.task_1.entities.UserEntity;
import com.example.task_1.exception.InvalidPasswordException;
import com.example.task_1.exception.UserAlreadyExistException;
import com.example.task_1.exception.UserNotFoundException;
import com.example.task_1.repositories.UserRepository;
import com.example.task_1.services.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final MappingUtils mappingUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MappingUtils mappingUtils) {
        this.userRepository = userRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public void create(UserDTO userDTO) throws UserAlreadyExistException {
        if(emailExist(userDTO.getEmail())){
            throw new UserAlreadyExistException("Пользователь с таким email уже существует: " + userDTO.getEmail());
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword())); // с этой строкой всё в порядке?
        userRepository.save(mappingUtils.mapToUserEntity(userDTO));
        // читать из базы заново read()
    }

    private boolean emailExist(String email){
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public List<UserDTO> readAll() {
        return userRepository.findAll().stream()
                .map(mappingUtils::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO read(UUID id) throws UserNotFoundException {
        if(userRepository.existsById(id)){
            return mappingUtils.mapToUserDto(userRepository.findById(id).orElse(new UserEntity()));
        } else {
            throw new UserNotFoundException("Пользователь с идентификатором '" + id + "' не найден!");
        }
    }

    @Override
    public UserDTO update(UUID id, UpdateUserDTO updDTO) throws UserNotFoundException {
        if (userRepository.existsById(id)){
            UserDTO userDTO = mappingUtils.mapToUserDto(userRepository.
                    findById(id).orElse(new UserEntity()));

            userDTO.setEmail(updDTO.getEmail());
            userDTO.setFamilyName(updDTO.getFamilyName());
            userDTO.setName(updDTO.getName());
            userDTO.setMiddleName(updDTO.getMiddleName());

            userRepository.save(mappingUtils.mapToUserEntity(userDTO));
            return userDTO;
        } else {
            throw new UserNotFoundException("Пользователь с идентификатором '" + id + "' не найден!");
        }
    }

    @Override
    public UserDTO updatePassword(UUID id, UserSetPasswordDTO uspDTO) throws UserNotFoundException, InvalidPasswordException {
        if(userRepository.existsById(id)){
            UserDTO userDTO = mappingUtils.mapToUserDto(userRepository.
                    findById(id).orElse(new UserEntity()));
            if(passwordEncoder.matches(uspDTO.getOldPassword(), userDTO.getPassword())) {
                if(Objects.equals( uspDTO.getNewPassword(),
                                   uspDTO.getConfirmNewPassword() )) {

                    userDTO.setPassword(passwordEncoder.encode( uspDTO.getNewPassword() ));

                    userRepository.save(mappingUtils.mapToUserEntity(userDTO));
                    return userDTO;
                } else {
                    throw new InvalidPasswordException("Ошибка при подтверждении нового пароля. Пароли не совпадают!");
                }
            } else {
                throw new InvalidPasswordException("Неверно указан старый пароль!");
            }
        } else {
            throw new UserNotFoundException("Пользователь с идентификатором '" + id + "' не найден!");
        }
    }

    @Override
    public UserDTO updateRole(UUID id, UserSetRoleDTO usrDTO) throws UserNotFoundException {
        if(userRepository.existsById(id)){
            UserDTO userDTO = mappingUtils.mapToUserDto(userRepository.
                    findById(id).orElse(new UserEntity()));

            userDTO.setRole(usrDTO.getRole());

            userRepository.save(mappingUtils.mapToUserEntity(userDTO)); // лишнее
            return userDTO;
        } else {
            throw new UserNotFoundException("Пользователь с идентификатором '" + id + "' не найден!");
        }
    }

    @Override
    public UserDTO setState (UUID id, Status state) throws UserNotFoundException {
        if(userRepository.existsById(id)){
            UserDTO userDTO = mappingUtils.mapToUserDto(userRepository.
                    findById(id).orElse(new UserEntity()));

            userDTO.setStatus(state);

            userRepository.save(mappingUtils.mapToUserEntity(userDTO)); // лишнее
            return userDTO;
        } else {
            throw new UserNotFoundException("Пользователь с идентификатором '" + id + "' не найден!");
        }
    }

    @Override
    public boolean delete(UUID id) throws UserNotFoundException {
        if(userRepository.existsById(id)) {
           userRepository.deleteById(id);
           return true;
        } else {
            throw new UserNotFoundException("Пользователь с идентификатором '" + id + "' не найден!");
        }
    }

}
