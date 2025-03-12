package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "user_oauth2")
public class UserOauth2Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false, unique = true)
    private String providerId;

    private String profilePicture;

    private Instant linkedAt = Instant.now();

    public UserOauth2Entity() {
    }

    public UserOauth2Entity(Long id,
                            UserEntity userEntity,
                            String provider,
                            String providerId,
                            String profilePicture,
                            Instant linkedAt) {
        this.id = id;
        this.userEntity = userEntity;
        this.provider = provider;
        this.providerId = providerId;
        this.profilePicture = profilePicture;
        this.linkedAt = linkedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    public Instant getLinkedAt() {
        return linkedAt;
    }

    public void setLinkedAt(Instant linkedAt) {
        this.linkedAt = linkedAt;
    }
}
