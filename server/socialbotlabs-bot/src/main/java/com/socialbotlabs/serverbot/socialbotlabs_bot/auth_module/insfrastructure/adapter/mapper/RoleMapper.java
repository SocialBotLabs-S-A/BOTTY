package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.mapper;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.Role;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.RoleEntity;

public class RoleMapper {

    private RoleMapper() {
    }

    public static Role toDomain(RoleEntity roleEntity){
        return new Role( roleEntity.getId(),roleEntity.getName());
    }

    public static RoleEntity toEntity(Role role){
       return new RoleEntity(role.getId(), role.getName());
    }

}
