package com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.persistense;

import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.domain.model.Client;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.domain.port.ClientRepositoryPort;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.mapper.ClientMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final JpaClientRepository jpaClientRepository;

    public ClientRepositoryAdapter(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public Optional<Client> findByClientId(String clientId) {
        return jpaClientRepository.findByClientId(clientId).map(ClientMapper::toDomain);
    }

    @Override
    public Optional<Client> createClient(Client client) {
        return Optional.of(jpaClientRepository.save(ClientMapper.toEntity(client))).map(ClientMapper::toDomain);
    }
}
