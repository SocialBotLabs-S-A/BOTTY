package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.mapper;

import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.UserOauth2;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa.UserOauth2Entity;

import java.time.Instant;

public final class UserOauth2Mapper {

    private UserOauth2Mapper() {
    }

    public static UserOauth2 toDomain(UserOauth2Entity userOauth2Entity) {
        return new UserOauth2(userOauth2Entity.getId(),
            UserMapper.toDomain(userOauth2Entity.getUserEntity()),
            userOauth2Entity.getProvider(),
            userOauth2Entity.getProviderId(),
            userOauth2Entity.getProfilePicture());
    }

    public static UserOauth2Entity toEntity(UserOauth2 userOauth2) {
        return new UserOauth2Entity(userOauth2.getId(),
            UserMapper.toEntity(userOauth2.getUser()),
            userOauth2.getProvider(),
            userOauth2.getProviderId(),
            userOauth2.getProfilePicture(),
            Instant.now());
    }
}
