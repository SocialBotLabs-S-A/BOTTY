package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.persistence.grantedpermission;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.GrantedPermission;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.ports.GrantedPermissionRepositoryPort;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.GrantedPermissionEntity;

import java.util.Optional;

public class GrantedPermissionRepositoryAdapter implements GrantedPermissionRepositoryPort {
    private final JpaGrantedPermissionRepository jpaGrantedPermissionRepository;

    public GrantedPermissionRepositoryAdapter(JpaGrantedPermissionRepository jpaGrantedPermissionRepository) {
        this.jpaGrantedPermissionRepository = jpaGrantedPermissionRepository;
    }
}
