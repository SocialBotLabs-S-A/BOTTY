package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.GrantedPermission;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.GrantedPermissionEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    //This class is a JPA entity that represents a user in the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String companyName;
    private String country;
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    //Roles
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "user")
    private Set<GrantedPermissionEntity> grantedPermissions = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(Long id, String fullName, String companyName, String phone, String country, String email, String password, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, boolean enabled, Set<GrantedPermissionEntity> grantedPermissions) {
        this.id = id;
        this.fullName = fullName;
        this.companyName = companyName;
        this.phone = phone;
        this.country = country;
        this.email = email;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.grantedPermissions = grantedPermissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<GrantedPermissionEntity> getGrantedPermissions() {
        return grantedPermissions;
    }

    public void setGrantedPermissions(Set<GrantedPermissionEntity> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }
}

