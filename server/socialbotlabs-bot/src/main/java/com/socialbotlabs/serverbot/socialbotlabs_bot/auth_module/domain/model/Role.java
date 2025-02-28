package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Role {
    private Long id;
    private RoleName roleName;
    private Set<GrantedPermission> grantedPermissionSet = new HashSet<>();

    public Role() {}

    public Role(Long id, RoleName roleName, Set<GrantedPermission> grantedPermissionSet) {
        this.id = id;
        this.roleName = roleName;
        this.grantedPermissionSet = grantedPermissionSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public Set<GrantedPermission> getGrantedPermissionSet() {
        return grantedPermissionSet;
    }

    public void setGrantedPermissionSet(Set<GrantedPermission> grantedPermissionSet) {
        this.grantedPermissionSet = grantedPermissionSet;
    }
}
