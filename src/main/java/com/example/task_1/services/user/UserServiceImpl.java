package com.example.task_1.services.user;


import com.example.task_1.dto.user.*;
import com.example.task_1.entities.RoleEntity;
import com.example.task_1.entities.Status;
import com.example.task_1.entities.UserEntity;
import com.example.task_1.exception.InvalidPasswordException;
import com.example.task_1.exception.RoleNotFoundException;
import com.example.task_1.exception.UserAlreadyExistException;
import com.example.task_1.exception.UserNotFoundException;
import com.example.task_1.repositories.RoleRepository;
import com.example.task_1.repositories.UserRepository;
import com.example.task_1.services.utils.MappingUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MappingUtils mappingUtils;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           MappingUtils mappingUtils,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mappingUtils = mappingUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public GetUserDTO create(CreateUserDTO createUserDTO) throws UserAlreadyExistException, RoleNotFoundException {
        if(emailExist(createUserDTO.getEmail())){
            throw new UserAlreadyExistException("Пользователь с таким email уже существует: " + createUserDTO.getEmail());
        }
        if(roleNotExist(createUserDTO.getRole())){
            throw new RoleNotFoundException("Роль с ID: '" + createUserDTO.getRole() + "' не существует");
        }

        UserEntity user = new UserEntity();

        user.setEmail(createUserDTO.getEmail());
        user.setFamilyName(createUserDTO.getFamilyName());
        user.setName(createUserDTO.getName());
        user.setMiddleName(createUserDTO.getMiddleName());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setRoleEntity(roleRepository.findById(createUserDTO.getRole()).orElse(new RoleEntity()));

        return mappingUtils.mapToGetUserDTO(userRepository.save(user));
    }

    private boolean emailExist(String email){
        return userRepository.findByEmail(email) != null;
    }

    private boolean roleNotExist(UUID role) { return !roleRepository.existsById(role); }

    @Override
    public List<GetUserDTO> readAll() {
        return userRepository.findAll().stream()
                .map(mappingUtils::mapToGetUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GetUserDTO read(UUID id) throws UserNotFoundException {
        if(userRepository.existsById(id)){
            return mappingUtils.mapToGetUserDTO(userRepository.findById(id).orElse(new UserEntity()));
        } else {
            throw new UserNotFoundException("Пользователь с идентификатором '" + id + "' не найден!");
        }
    }

    @Override
    public GetUserDTO update(UUID id, UpdateUserDTO updDTO) throws UserNotFoundException, UserAlreadyExistException {
        if (userRepository.existsById(id)){
            if(emailExist(updDTO.getEmail())){
                throw new UserAlreadyExistException("Пользователь с таким email уже существует: " + updDTO.getEmail());
            }
            UserEntity userEntity = userRepository.findById(id).orElse(new UserEntity());

            userEntity.setEmail(updDTO.getEmail());
            userEntity.setFamilyName(updDTO.getFamilyName());
            userEntity.setName(updDTO.getName());
            userEntity.setMiddleName(updDTO.getMiddleName());

            userRepository.save(userEntity);
            return mappingUtils.mapToGetUserDTO(userEntity);
        } else {
            throw new UserNotFoundException("Пользователь с идентификатором '" + id + "' не найден!");
        }
    }

    @Override
    public GetUserDTO updatePassword(UUID id, UserSetPasswordDTO uspDTO) throws UserNotFoundException, InvalidPasswordException {
        if(userRepository.existsById(id)){
            UserEntity userEntity = userRepository.findById(id).orElse(new UserEntity());
            if(passwordEncoder.matches(uspDTO.getOldPassword(), userEntity.getPassword())) {
                if(Objects.equals( uspDTO.getNewPassword(),
                                   uspDTO.getConfirmNewPassword() )) {

                    userEntity.setPassword(passwordEncoder.encode(uspDTO.getNewPassword()));

                    userRepository.save(userEntity);
                    return mappingUtils.mapToGetUserDTO(userEntity);
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
    public GetUserDTO updateRole(UUID id, UserSetRoleDTO usrDTO) throws UserNotFoundException, RoleNotFoundException {
        if(userRepository.existsById(id)){
            if(roleNotExist(usrDTO.getRole())) {
                throw new RoleNotFoundException("Роль с ID: '" + usrDTO.getRole() + "' не существует");
            }

            UserEntity userEntity = userRepository.findById(id).orElse(new UserEntity());
            userEntity.setRoleEntity(roleRepository.findById(usrDTO.getRole()).orElse(new RoleEntity()));

            userRepository.save(userEntity);
            return mappingUtils.mapToGetUserDTO(userEntity);
        } else {
            throw new UserNotFoundException("Пользователь с идентификатором '" + id + "' не найден!");
        }
    }

    @Override
    public GetUserDTO setState (UUID id, Status state) throws UserNotFoundException {
        if(userRepository.existsById(id)){
            UserEntity userEntity = userRepository.findById(id).orElse(new UserEntity());

            userEntity.setStatus(state);

            userRepository.save(userEntity);
            return mappingUtils.mapToGetUserDTO(userEntity);
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
