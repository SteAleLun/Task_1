package com.example.task_1.services;

import com.example.task_1.dto.UpdateUserDTO;
import com.example.task_1.dto.UserDTO;
import com.example.task_1.dto.UserSetPasswordDTO;
import com.example.task_1.entities.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    /**
     * Создает нового пользователя
     * @param userDTO - пользователь для создания
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
     * @param updDTO - пользователь в соответсвии с которым нужно обновить данные
     * @param id - id пользователя которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    UserDTO update(UUID id, UpdateUserDTO updDTO);

    /**
     * Изменение пароля пользователя с заданным ID,
     * в соответствии с переданным пользователем
     * @param id - id пользователя, пароль которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean updatePassword(UUID id, UserSetPasswordDTO uspDTO);

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
