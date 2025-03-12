package com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.persistense;

import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.mapper.ClientMapper;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisteredClientService implements RegisteredClientRepository {

    private final JpaClientRepository jpaClientRepository;

    public RegisteredClientService(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        //This method is unnecessary
    }

    @Override
    public RegisteredClient findById(String id) {
        return jpaClientRepository.findByClientId(id)
            .map(ClientMapper::toDomain)
            .map(ClientMapper::toRegisteredClient)
            .orElseThrow(() -> new RuntimeException("Could not find registered client"));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return findById(clientId);
    }
}
