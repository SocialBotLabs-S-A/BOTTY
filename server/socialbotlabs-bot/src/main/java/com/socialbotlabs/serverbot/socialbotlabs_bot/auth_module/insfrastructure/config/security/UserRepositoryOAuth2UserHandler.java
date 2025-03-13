package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.config.security;

import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.application.service.UserOauth2Service;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.function.Consumer;

public final class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {

    private final UserOauth2Service userOauth2Service;

    public UserRepositoryOAuth2UserHandler(UserOauth2Service userOauth2Service) {
        this.userOauth2Service = userOauth2Service;
    }

    @Override
    public void accept(OAuth2User user) {

        if (!userOauth2Service.existsOauth2User(user)) {
            userOauth2Service.registerNewOauth2User(user);
        }
    }
}
