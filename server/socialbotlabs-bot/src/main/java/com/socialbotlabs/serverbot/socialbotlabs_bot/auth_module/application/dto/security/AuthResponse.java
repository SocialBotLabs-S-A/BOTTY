package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.dto.security;
public class AuthResponse {
    private String token;
    private String message;

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // Getters and setters...
}
