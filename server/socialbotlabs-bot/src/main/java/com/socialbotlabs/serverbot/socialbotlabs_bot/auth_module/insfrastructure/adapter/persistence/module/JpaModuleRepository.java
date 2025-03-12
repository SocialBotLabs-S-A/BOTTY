package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.persistence.module;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaModuleRepository extends JpaRepository<ModuleEntity,Long> {
}
