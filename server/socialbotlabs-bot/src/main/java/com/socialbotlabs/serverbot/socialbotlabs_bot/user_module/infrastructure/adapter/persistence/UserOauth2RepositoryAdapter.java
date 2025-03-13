package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.persistence;

import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.User;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.UserOauth2;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.ports.UserOauth2RepositoryPort;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.mapper.UserMapper;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.mapper.UserOauth2Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

//This class is an adapter that implements the UserRepositoryPort interface.
//It allows us to use the JPA repository in the service layer.
@Component
public class UserOauth2RepositoryAdapter implements UserOauth2RepositoryPort {

    private final JpaUserOauth2Repository jpaUserOauth2Repository;

    public UserOauth2RepositoryAdapter(JpaUserOauth2Repository jpaUserOauth2Repository) {
        this.jpaUserOauth2Repository = jpaUserOauth2Repository;
    }

    @Override
    public Optional<UserOauth2> findByUser(User user) {
        return jpaUserOauth2Repository.findByUser(UserMapper.toEntity(user)).map(UserOauth2Mapper::toDomain);
    }

    @Override
    public void saveUserOauth2(UserOauth2 userOauth2) {
        jpaUserOauth2Repository.save(UserOauth2Mapper.toEntity(userOauth2));
    }

    @Override
    public boolean existsByProviderAndProviderUserId(String provider, String providerUserId) {
        return jpaUserOauth2Repository.existsByProviderAndProviderId(provider,providerUserId);
    }
}
