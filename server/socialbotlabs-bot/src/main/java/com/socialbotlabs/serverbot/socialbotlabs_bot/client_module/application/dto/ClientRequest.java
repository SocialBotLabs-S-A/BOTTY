package com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.application.dto;

import java.util.List;

public class ClientRequest {

    private String clientId;

    private List<String> clientAuthenticationMethods;

    private List<String> authorizationGrantTypes;

    private List<String> redirectUris;

    private List<String> scopes;

    private Long durationInMinutes;

    private Long refreshTokenDurationTimes;

    private Boolean requiredProofKey;

    public ClientRequest() {
    }

    public ClientRequest(String clientId,
                         List<String> clientAuthenticationMethods,
                         List<String> authorizationGrantTypes,
                         List<String> redirectUris,
                         List<String> scopes,
                         Long durationInMinutes,
                         Long refreshTokenDurationTimes,
                         Boolean requiredProofKey) {
        this.clientId = clientId;
        this.clientAuthenticationMethods = clientAuthenticationMethods;
        this.authorizationGrantTypes = authorizationGrantTypes;
        this.redirectUris = redirectUris;
        this.scopes = scopes;
        this.durationInMinutes = durationInMinutes;
        this.refreshTokenDurationTimes = refreshTokenDurationTimes;
        this.requiredProofKey = requiredProofKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<String> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(List<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public List<String> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(List<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public List<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
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
