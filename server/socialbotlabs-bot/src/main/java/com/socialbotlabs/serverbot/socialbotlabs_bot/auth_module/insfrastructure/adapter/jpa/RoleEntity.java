package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa;


import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.RoleName;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;


    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<GrantedPermissionEntity> grantedPermissionSet = new HashSet<>();

    public RoleEntity() {
    }

    public RoleEntity(Long id, Set<GrantedPermissionEntity> grantedPermissionSet, RoleName roleName) {
        this.id = id;
        this.grantedPermissionSet = grantedPermissionSet;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<GrantedPermissionEntity> getGrantedPermissionSet() {
        return grantedPermissionSet;
    }

    public void setGrantedPermissionSet(Set<GrantedPermissionEntity> grantedPermissionSet) {
        this.grantedPermissionSet = grantedPermissionSet;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}