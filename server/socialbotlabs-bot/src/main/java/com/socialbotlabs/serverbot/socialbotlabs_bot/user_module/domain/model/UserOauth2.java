package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model;

public class UserOauth2 {

    private Long id;

    private User user;

    private String provider;

    private String providerId;

    private String profilePicture;

    public UserOauth2() {
    }

    public UserOauth2(Long id, User user, String provider, String providerId, String profilePicture) {
        this.id = id;
        this.user = user;
        this.provider = provider;
        this.providerId = providerId;
        this.profilePicture = profilePicture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
