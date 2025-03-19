package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.persistence;


import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa.UserEntity;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa.UserOauth2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserOauth2Repository extends JpaRepository<UserOauth2Entity, Long> {
    Optional<UserOauth2Entity> findByUserEntity(UserEntity userEntity);

    boolean existsByProviderAndProviderId(String provider, String providerUserId);
}
