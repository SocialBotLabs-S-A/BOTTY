package com.socialbotlabs.serverbot.socialbotlabs_bot.client_module.application.dto;

import java.util.List;

public class ClientResponse {

    private String clientId;
    private String clientSecret;
    private List<String> redirectUris;
    private List<String> scopes;

    public ClientResponse() {
    }

    public ClientResponse(String clientId, String clientSecret, List<String> redirectUris, List<String> scopes) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUris = redirectUris;
        this.scopes = scopes;
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
}
