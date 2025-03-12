package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.persistence;

import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//This interface extends JpaRepository, which provides basic CRUD operations.
@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
