package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.persistence.role;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
