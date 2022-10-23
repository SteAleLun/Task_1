package com.example.task_1.service;

import com.example.task_1.model.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RoleServiceImpl implements RoleService {

    // Хранилище ролей пользователей
    private static final Map<Integer, Role> ROLE_REPOSITORY_MAP = new HashMap<>();

    // Переменная для геренации ID роли пользователя
    private static final AtomicInteger ROLE_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Role role) {
        final int roleId = ROLE_ID_HOLDER.incrementAndGet();
        role.setId(roleId);
        ROLE_REPOSITORY_MAP.put(roleId, role);
    }

    @Override
    public List<Role> readAll() {
        return new ArrayList<>(ROLE_REPOSITORY_MAP.values());
    }

    @Override
    public Role read(int id) {
        return ROLE_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Role role, int id) {
        if(ROLE_REPOSITORY_MAP.containsKey(id)){
            role.setId(id);
            ROLE_REPOSITORY_MAP.put(id, role);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return ROLE_REPOSITORY_MAP.remove(id) != null;
    }
}
