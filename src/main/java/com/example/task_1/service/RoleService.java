package com.example.task_1.service;



import com.example.task_1.model.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    /**
     * Создает нового клиента
     * @param role - пользователь для создания
     */
    void create(Role role);

    /**
     * Возвращает список всех имеющихся пользователей
     * @return список пользователей
     */
    List<Role> readAll();

    /**
     * Возвращает клиента по его ID
     * @param id - ID клиента
     * @return - объект пользователя с заданным ID
     */
    //Role read(int id);
    Role read(UUID id);

    /**
     * Обновляет пользователя с заданным ID,
     * в соответствии с переданным пользователем
     * @param role - пользователя в соответсвии с которым нужно обновить данные
     * @param id - id пользователя которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    //boolean update(Role role, int id);
    boolean update(Role role, UUID id);

    /**
     * Удаляет пользователя с заданным ID
     * @param id - id пользователя, которого нужно удалить
     * @return - true если пользователь был удален, иначе false
     */
    boolean delete(UUID id);
}
