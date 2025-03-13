package com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.persistense;

import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.jpa.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByClientId(String clientId);
}
