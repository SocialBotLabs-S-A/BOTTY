package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.persistence.grantedpermission;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.GrantedPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JpaGrantedPermissionRepository extends JpaRepository<GrantedPermissionEntity, Long> {
}
