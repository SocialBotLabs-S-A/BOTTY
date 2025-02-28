package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model;

import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.User;

public class GrantedPermission {
    private Long id;
    private User user;
    private Role role;

    public GrantedPermission() {
    }

    public GrantedPermission(Long id, Role role, User user) {
        this.id = id;
        this.role = role;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
