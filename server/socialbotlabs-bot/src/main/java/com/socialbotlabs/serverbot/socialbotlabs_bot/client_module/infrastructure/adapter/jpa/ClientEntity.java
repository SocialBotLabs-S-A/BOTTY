package com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.infrastructure.adapter.jpa;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class ClientEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;

    private String clientSecret;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> clientAuthenticationMethods;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorizationGrantTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> redirectUris;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> scopes;

    private Long durationInMinutes;

    private Long refreshTokenDurationTimes;

    private Boolean requiredProofKey;

    public ClientEntity() {
    }

    public ClientEntity(
        Long id,
        String clientId,
        String clientSecret,
        List<String> clientAuthenticationMethods,
        List<String> authorizationGrantTypes,
        List<String> redirectUris,
        List<String> scopes,
        Long durationInMinutes,
        Long refreshTokenDurationTimes,
        Boolean requiredProofKey) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientAuthenticationMethods = new ArrayList<>(clientAuthenticationMethods);
        this.authorizationGrantTypes = new ArrayList<>(authorizationGrantTypes);
        this.redirectUris = new ArrayList<>(redirectUris);
        this.scopes = new ArrayList<>(scopes);
        this.durationInMinutes = durationInMinutes;
        this.refreshTokenDurationTimes = refreshTokenDurationTimes;
        this.requiredProofKey = requiredProofKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public List<String> getClientAuthenticationMethods() {
        return new ArrayList<>(clientAuthenticationMethods);
    }

    public void setClientAuthenticationMethods(List<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = new ArrayList<>(clientAuthenticationMethods);
    }

    public List<String> getAuthorizationGrantTypes() {
        return new ArrayList<>(authorizationGrantTypes);
    }

    public void setAuthorizationGrantTypes(List<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = new ArrayList<>(authorizationGrantTypes);
    }

    public List<String> getRedirectUris() {
        return new ArrayList<>(redirectUris);
    }

    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = new ArrayList<>(redirectUris);
    }

    public List<String> getScopes() {
        return new ArrayList<>(scopes);
    }

    public void setScopes(List<String> scopes) {
        this.scopes = new ArrayList<>(scopes);
    }

    public Long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Long getRefreshTokenDurationTimes() {
        return refreshTokenDurationTimes;
    }

    public void setRefreshTokenDurationTimes(Long refreshTokenDurationTimes) {
        this.refreshTokenDurationTimes = refreshTokenDurationTimes;
    }

    public Boolean getRequiredProofKey() {
        return requiredProofKey;
    }

    public void setRequiredProofKey(Boolean requiredProofKey) {
        this.requiredProofKey = requiredProofKey;
    }
}
