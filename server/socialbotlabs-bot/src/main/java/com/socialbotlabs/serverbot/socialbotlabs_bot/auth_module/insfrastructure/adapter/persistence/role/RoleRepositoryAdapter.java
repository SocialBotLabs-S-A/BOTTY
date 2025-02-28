package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.persistence.role;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.Role;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.ports.RoleRepositoryPort;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleRepositoryAdapter implements RoleRepositoryPort {
    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryAdapter(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Optional<Role> findByRoleName(String name) {
        return jpaRoleRepository.findByRoleName(name).map(this::toDomain);
    }
    private Role toDomain(RoleEntity entity) {
        Role role = new Role();
        role.setId(entity.getId());
        role.setRoleName(entity.getRoleName());
        return role;
    }
    private RoleEntity toEntity(Role role) {
        RoleEntity entity = new RoleEntity();
        entity.setId(role.getId());
        entity.setRoleName(role.getRoleName());
        return entity;
    }
}
