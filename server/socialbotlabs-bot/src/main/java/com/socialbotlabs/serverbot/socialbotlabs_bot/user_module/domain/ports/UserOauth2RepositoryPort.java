package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.ports;

import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.User;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.UserOauth2;

import java.util.Optional;

public interface UserOauth2RepositoryPort {
    Optional<UserOauth2> findByUser(User user);
    void saveUserOauth2(UserOauth2 userOauth2);

    boolean existsByProviderAndProviderUserId(String provider, String providerUserId);
}
