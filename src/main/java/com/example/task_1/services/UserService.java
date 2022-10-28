package com.example.task_1.services;

import com.example.task_1.dto.UserDTO;
import com.example.task_1.entities.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    /**
     * Создает нового пользователя
     * @param userEntity - пользователь для создания
     */
    void create(UserDTO userDTO);

    /**
     * Возвращает список всех имеющихся пользователей
     * @return список пользователей
     */
    List<UserDTO> readAll();

    /**
     * Возвращает пользователя по его ID
     *
     * @param id - ID пользователя
     * @return - объект пользователя с заданным ID
     */
    //User read(int id);
    UserDTO read(UUID id);

    /**
     * Обновляет пользователя с заданным ID,
     * в соответствии с переданным пользователем
     * @param userEntity - пользователь в соответсвии с которым нужно обновить данные
     * @param id - id пользователя которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(UUID id, UserEntity userEntity);

    /**
     * Изменение пароля пользователя с заданным ID,
     * в соответствии с переданным пользователем
     * @param id - id пользователя, пароль которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean updatePassword(UUID id, UserEntity userEntity, String oldPassword, String password);

    /**
     * Изменение роли пользователя с заданным ID,
     * в соответствии с переданным пользователем
     * @param id - id пользователя, пароль которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean updateRole(UUID id);


    /**
     * Удаляет пользователя с заданным ID
     * @param id - id пользователя, которого нужно удалить
     * @return - true если пользователь был удален, иначе false
     */
    boolean delete(UUID id);



}
