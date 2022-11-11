package com.example.task_1.services.role;

import com.example.task_1.dto.role.RoleDTO;
import com.example.task_1.dto.role.UpdateRoleDTO;
import com.example.task_1.entities.RoleEntity;
import com.example.task_1.exception.RoleNotFoundException;
import com.example.task_1.repositories.RoleRepository;
import com.example.task_1.services.utils.MappingUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MappingUtils mappingUtils;

    public RoleServiceImpl(RoleRepository roleRepository, MappingUtils mappingUtils) {
        this.roleRepository = roleRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public void create(RoleDTO roleDTO) {
        roleRepository.save(mappingUtils.mapToRoleEntity(roleDTO));
    }

    // получить список ролей
    @Override
    public List<RoleDTO> readAll() {
        return roleRepository.findAll().stream()
                .map(mappingUtils::mapToRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO read(UUID id) throws RoleNotFoundException {
        if(roleRepository.existsById(id)) {
            return mappingUtils.mapToRoleDto(roleRepository.findById(id).orElse(new RoleEntity()));
        } else {
            throw new RoleNotFoundException("Роль с идентификатором '" + id + "' не найдена!");
        }
    }

    @Override
    public RoleDTO update(UUID id, UpdateRoleDTO updDTO) throws RoleNotFoundException {
        if (roleRepository.existsById(id)) {
            RoleDTO roleDTO = new RoleDTO();

            roleDTO.setName(updDTO.getName());
            roleDTO.setDescription(updDTO.getDescription());

            roleRepository.save(mappingUtils.mapToRoleEntity(roleDTO));
            return roleDTO;
        } else {
            throw new RoleNotFoundException("Роль с идентификатором '" + id + "' не найдена!");
        }
    }

    @Override
    public boolean delete(UUID id) throws RoleNotFoundException {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        } else {
            throw new RoleNotFoundException("Роль с идентификатором '" + id + "' не найдена!");
        }
    }

}
