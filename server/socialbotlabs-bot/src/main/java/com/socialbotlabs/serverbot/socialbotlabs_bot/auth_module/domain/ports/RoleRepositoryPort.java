package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.ports;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.Role;

import java.util.Optional;

public interface RoleRepositoryPort {
    Optional<Role> findByRoleName(String name);
}
