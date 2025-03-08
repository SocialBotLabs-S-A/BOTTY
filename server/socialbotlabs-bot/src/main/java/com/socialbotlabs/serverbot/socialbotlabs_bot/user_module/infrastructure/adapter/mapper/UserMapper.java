package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.mapper;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.mapper.RoleMapper;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.User;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa.UserEntity;

public class UserMapper {

    private UserMapper() {
    }

    // Convert UserEntity to User (domain model)
    public static User toDomain(UserEntity entity) {
        User user = new User(
            entity.getId(),
            entity.getFullName(),
            entity.getUsername(),
            entity.getCompanyName(),
            entity.getCountry(),
            entity.getPhone(),
            entity.getEmail(),
            "");
        user.setRole(RoleMapper.toDomain(entity.getRoleEntity()));
        return user;
    }

    // Convert User (domain model) to UserEntity
    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity(
            user.getFullName(),
            user.getUsername(),
            user.getCompanyName(),
            user.getCountry(),
            user.getPhone(),
            user.getEmail(),
            user.getPassword());
        userEntity.setRoleEntity(RoleMapper.toEntity(user.getRole()));
        return userEntity;
    }
}
