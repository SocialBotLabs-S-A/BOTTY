package com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.domain.port;

import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.domain.model.Client;

import java.util.Optional;

public interface ClientRepositoryPort {
    Optional<Client> findByClientId(String clientId);
    Optional<Client> createClient(Client client);
}
