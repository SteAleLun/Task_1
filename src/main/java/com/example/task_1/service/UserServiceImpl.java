package com.example.task_1.service;

import com.example.task_1.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {
    // Хранилище пользователей
    public static final Map<UUID, User> USER_REPOSITORY_MAP = new HashMap<>();

    /*
     //Переменная для генерации ID пользователя
    public static final AtomicInteger USER_ID_HOLDER = new AtomicInteger();
    */


    @Override
    public void create(User user) {
        //final int userId = USER_ID_HOLDER.incrementAndGet();
        final UUID userId = UUID.randomUUID();
        user.setId(userId);
        USER_REPOSITORY_MAP.put(userId, user);
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(USER_REPOSITORY_MAP.values());
    }

    @Override
    public User read(UUID id) {
        return USER_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(User user, UUID id) {
        if (USER_REPOSITORY_MAP.containsKey(id)){
            user.setId(id);
            USER_REPOSITORY_MAP.put(id, user);
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        return USER_REPOSITORY_MAP.remove(id) != null;
    }
}
