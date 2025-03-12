package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.persistence.role;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.Role;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.ports.RoleRepositoryPort;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.mapper.RoleMapper;
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
        return jpaRoleRepository.findByName(name).map(RoleMapper::toDomain);
    }
}
