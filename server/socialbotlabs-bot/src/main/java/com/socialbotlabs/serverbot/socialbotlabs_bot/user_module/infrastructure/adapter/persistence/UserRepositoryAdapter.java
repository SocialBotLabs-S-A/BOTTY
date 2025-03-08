package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.persistence;

import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.User;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.ports.UserRepositoryPort;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa.UserEntity;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

//This class is an adapter that implements the UserRepositoryPort interface.
//It allows us to use the JPA repository in the service layer.
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryAdapter.class);

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public boolean saveUser(User user) {
        try{
            UserEntity entity = UserMapper.toEntity(user);
            jpaUserRepository.save(entity);
            return true;
        }catch (Exception e){
            LOGGER.error("Error saving user: {}", e.getMessage());
            return false;
        }
    }
}
