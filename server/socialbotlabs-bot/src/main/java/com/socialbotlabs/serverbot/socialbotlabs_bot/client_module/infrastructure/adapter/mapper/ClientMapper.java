package com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.mapper;

import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.application.dto.ClientRequest;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.application.dto.ClientResponse;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.domain.model.Client;
import com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.jpa.ClientEntity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

public final class ClientMapper {

    private ClientMapper(){}

    public static Client toDomain(ClientEntity clientEntity){
        return new Client(clientEntity.getId(),
                clientEntity.getClientId(),
                clientEntity.getClientSecret(),
                clientEntity.getClientAuthenticationMethods(),
                clientEntity.getAuthorizationGrantTypes(),
                clientEntity.getRedirectUris(),
                clientEntity.getScopes(),
                clientEntity.getDurationInMinutes(),
                clientEntity.getRefreshTokenDurationTimes(),
                clientEntity.getRequiredProofKey());
    }

    public static ClientEntity toEntity(Client client){
        return new ClientEntity(client.getId(),
                client.getClientId(),
                client.getClientSecret(),
                client.getClientAuthenticationMethods(),
                client.getAuthorizationGrantTypes(),
                client.getRedirectUris(),
                client.getScopes(),
                client.getDurationInMinutes(),
                client.getRefreshTokenDurationTimes(),
                client.getRequiredProofKey());
    }

    public static RegisteredClient toRegisteredClient(Client client) {
        return RegisteredClient.withId(client.getClientId())
            .clientId(client.getClientId())
            .clientSecret(client.getClientSecret())
            .clientIdIssuedAt(Instant.now())
            .clientAuthenticationMethods((Set<ClientAuthenticationMethod> clientAuthMethods) -> {
                clientAuthMethods.clear();
                client.getClientAuthenticationMethods()
                    .forEach(authMethods -> clientAuthMethods.add(new ClientAuthenticationMethod((authMethods))));
            })
            .authorizationGrantTypes((Set<AuthorizationGrantType> authGranTypes) -> {
                authGranTypes.clear();
                client.getAuthorizationGrantTypes()
                    .forEach(granTypes -> authGranTypes.add(new AuthorizationGrantType(granTypes)));
            })
            .redirectUris((Set<String> redirectUris) -> {
                redirectUris.clear();
                redirectUris.addAll(client.getRedirectUris());
            })
            .scopes((Set<String> scopes) -> {
                scopes.clear();
                scopes.addAll(client.getScopes());
            })
            .tokenSettings(TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofMinutes(client.getDurationInMinutes()))
                .refreshTokenTimeToLive(Duration.
                    ofMinutes(client.getDurationInMinutes() * client.getRefreshTokenDurationTimes()))
                .build())
            .clientSettings(ClientSettings.builder()
                .requireProofKey(client.getRequiredProofKey())
                .build())
            .build();
    }

    public static Client toDomain(ClientRequest clientRequest) {
        return new Client(null,
            clientRequest.getClientId(),
            null,
            clientRequest.getClientAuthenticationMethods(),
            clientRequest.getAuthorizationGrantTypes(),
            clientRequest.getRedirectUris(),
            clientRequest.getScopes(),
            clientRequest.getDurationInMinutes(),
            clientRequest.getRefreshTokenDurationTimes(),
            clientRequest.getRequiredProofKey());
    }

    public static ClientResponse toDto(Client client) {
        return new ClientResponse(client.getClientId(),
                client.getClientSecret(),
                client.getRedirectUris(),
                client.getScopes());
    }
}
