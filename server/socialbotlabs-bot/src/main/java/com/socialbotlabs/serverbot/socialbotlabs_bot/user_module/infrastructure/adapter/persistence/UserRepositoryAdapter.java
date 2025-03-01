package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.persistence;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.GrantedPermission;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.Role;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.GrantedPermissionEntity;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.RoleEntity;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.User;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.ports.UserRepositoryPort;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

//This class is an adapter that implements the UserRepositoryPort interface.
//It allows us to use the JPA repository in the service layer.
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {
    @Autowired
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity saved = jpaUserRepository.save(entity);
        return toDomain(saved);
    }

    // Convert UserEntity to User (domain model)
    private User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setFullName(entity.getFullName());
        user.setCompanyName(entity.getCompanyName());
        user.setCountry(entity.getCountry());
        user.setPhone(entity.getPhone());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setGrantedPermissions(entity.getGrantedPermissions().stream().map(this::toDomain).collect(Collectors.toSet())
        );
        return user;
    }
    // Convert User (domain model) to UserEntity
    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFullName(user.getFullName());
        entity.setCompanyName(user.getCompanyName());
        entity.setCountry(user.getCountry());
        entity.setPhone(user.getPhone());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }
    private GrantedPermission toDomain(GrantedPermissionEntity entity) {
        GrantedPermission grantedPermission = new GrantedPermission();
        grantedPermission.setId(entity.getId());
        grantedPermission.setUser(toDomain(entity.getUser()));
        grantedPermission.setRole(toDomain(entity.getRole()));
        return grantedPermission;
    }
    private GrantedPermissionEntity toEntity(GrantedPermission grantedPermission) {
        GrantedPermissionEntity entity = new GrantedPermissionEntity();
        entity.setId(grantedPermission.getId());
        entity.setUser(toEntity(grantedPermission.getUser()));
        return entity;
    }
    private Role toDomain(RoleEntity entity) {
        Role role = new Role();
        role.setId(entity.getId());
        role.setRoleName(entity.getRoleName());
        return role;
    }
}
