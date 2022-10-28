package com.example.task_1.services;



import com.example.task_1.entities.RoleEntity;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    /**
     * Создает нового клиента
     * @param roleEntity - пользователь для создания
     */
    void create(RoleEntity roleEntity);

    /**
     * Возвращает список всех имеющихся пользователей
     * @return список пользователей
     */
    List<RoleEntity> readAll();

    /**
     * Возвращает клиента по его ID
     * @param id - ID клиента
     * @return - объект пользователя с заданным ID
     */
    //Role read(int id);
    RoleEntity read(UUID id);

    /**
     * Обновляет пользователя с заданным ID,
     * в соответствии с переданным пользователем
     * @param roleEntity - пользователя в соответсвии с которым нужно обновить данные
     * @param id - id пользователя которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    //boolean update(Role role, int id);
    boolean update(RoleEntity roleEntity, UUID id);

    /**
     * Удаляет пользователя с заданным ID
     * @param id - id пользователя, которого нужно удалить
     * @return - true если пользователь был удален, иначе false
     */
    boolean delete(UUID id);
}
