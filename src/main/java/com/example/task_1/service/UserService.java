package com.example.task_1.service;

import com.example.task_1.model.User;

import java.util.List;

public interface UserService {
    /**
     * Создает нового клиента
     * @param user - пользователь для создания
     */
    void create(User user);

    /**
     * Возвращает список всех имеющихся пользователей
     * @return список пользователей
     */
    List<User> readAll();

    /**
     * Возвращает клиента по его ID
     * @param id - ID клиента
     * @return - объект пользователя с заданным ID
     */
    User read(int id);

    /**
     * Обновляет пользователя с заданным ID,
     * в соответствии с переданным пользователем
     * @param client - пользователя в соответсвии с которым нужно обновить данные
     * @param id - id пользователя которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(User client, int id);

    /**
     * Удаляет пользователя с заданным ID
     * @param id - id пользователя, которого нужно удалить
     * @return - true если пользователь был удален, иначе false
     */
    boolean delete(int id);
}
