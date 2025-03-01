package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa;

import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "granted_permissions")
public class GrantedPermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship to UserEntity
    // Relación con UserEntity
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Relationship to RoleEntity
    // Relación con RoleEntity
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public GrantedPermissionEntity(Long id, UserEntity user, RoleEntity role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public GrantedPermissionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
