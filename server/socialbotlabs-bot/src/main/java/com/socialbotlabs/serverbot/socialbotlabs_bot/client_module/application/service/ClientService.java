package com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.application.service;

import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.application.dto.ClientRequest;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.application.dto.ClientResponse;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.domain.model.Client;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.mapper.ClientMapper;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.persistense.ClientRepositoryAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class ClientService {

    private static final int LONG_CLIENT_SECRET = 32;

    private final ClientRepositoryAdapter clientRepositoryAdapter;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepositoryAdapter clientRepositoryAdapter, PasswordEncoder passwordEncoder) {
        this.clientRepositoryAdapter = clientRepositoryAdapter;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<ClientResponse> createClient(ClientRequest clientRequest) {
        //1. Convert to Domain
        Client client = ClientMapper.toDomain(clientRequest);
        //2. Generate Random client Secret
        String clientSecret = generateClientSecret(LONG_CLIENT_SECRET);
        client.setClientSecret(passwordEncoder.encode(clientSecret));
        //3. Save to Database
        Optional<Client> savedClient = clientRepositoryAdapter.createClient(client);
        //4. Convert to Response
        return savedClient.map(client1 -> {
            client1.setClientSecret(clientSecret);
            return ClientMapper.toDto(client1);
        });
    }

    public static String generateClientSecret(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretBytes = new byte[length];
        secureRandom.nextBytes(secretBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(secretBytes);
    }
}
