package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.service.security;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.dto.security.AuthResponse;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.dto.security.LoginRequest;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.dto.security.RegisterRequest;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.GrantedPermission;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.Role;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.GrantedPermissionEntity;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa.RoleEntity;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.persistence.role.RoleRepositoryAdapter;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.config.security.JwtUtil;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.User;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.jpa.UserEntity;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.persistence.UserRepositoryAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final UserRepositoryAdapter userRepository;
    private final JwtUtil jwtUtil;
    private final RoleRepositoryAdapter roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepositoryAdapter userRepository, JwtUtil jwtUtil, RoleRepositoryAdapter roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    public AuthResponse login(LoginRequest request) {
        // 1. Find user by email
        User userEntity = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // 2. Check password
        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 3. Generate JWT
        // Convert userEntity to domain model or directly use userEntity to extract roles
        String token = jwtUtil.tokensGenerator(userEntity);

        // 4. Return token to the front
        return new AuthResponse(token, "Login successful");
    }
    public AuthResponse register(RegisterRequest request) {
        // 1. Check if the email is already registered / Verificar si el email ya está registrado
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        // 2. Create a new user entity / Crear una nueva entidad de usuario
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(request.getFullName());
        userEntity.setCompanyName(request.getCompanyName());
        userEntity.setCountry(request.getCountry());
        userEntity.setPhone(request.getPhone());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        // By default, set account enabled / Configurar el usuario habilitado por defecto
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);

        // 3. Assign a default role (e.g. ROLE_USER) / Asignar un rol por defecto (ej. ROLE_USER)
        Role roleEntity = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Create a GrantedPermissionEntity to link the user with the role
        // Crear GrantedPermissionEntity para enlazar el usuario con el rol
        GrantedPermissionEntity grantedPermission = new GrantedPermissionEntity();
        grantedPermission.setUser(userEntity);
        grantedPermission.setRole(toEntity(roleEntity));
        userEntity.getGrantedPermissions().add(grantedPermission);


        // Add the permission to the user's set / Agregar el permiso al set del usuario
        userEntity.getGrantedPermissions().add(grantedPermission);

        // 4. Save the user in the database / Guardar el usuario en la base de datos
        userRepository.save(toDomain(userEntity));
        // 5. Generate a JWT token (optional) / Generar un token JWT (opcional)
        String token = jwtUtil.tokensGenerator(toDomain(userEntity));

        // 6. Return AuthResponse with the token and a message / Retornar la respuesta con el token y un mensaje
        return new AuthResponse(token, "User registered successfully");
    }
    private RoleEntity toEntity(Role role) {
        RoleEntity entity = new RoleEntity();
        entity.setId(role.getId());
        entity.setRoleName(role.getRoleName());
        return entity;
    }
    private Role toDomain(RoleEntity entity) {
        Role role = new Role();
        role.setId(entity.getId());
        role.setRoleName(entity.getRoleName());
        return role;
    }
    private GrantedPermission toDomain(GrantedPermissionEntity entity) {
        GrantedPermission grantedPermission = new GrantedPermission();
        grantedPermission.setId(entity.getId());
        grantedPermission.setUser(toDomain(entity.getUser()));
        grantedPermission.setRole(toDomain(entity.getRole()));
        return grantedPermission;
    }
    private User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setFullName(entity.getFullName());
        user.setCompanyName(entity.getCompanyName());
        user.setCountry(entity.getCountry());
        user.setPhone(entity.getPhone());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setGrantedPermissions(entity.getGrantedPermissions().stream()
                .map(this::toDomain)
                .collect(Collectors.toSet()));
        return user;
    }
    private GrantedPermissionEntity toEntity(GrantedPermission grantedPermission) {
        GrantedPermissionEntity entity = new GrantedPermissionEntity();
        entity.setId(grantedPermission.getId());
        entity.setUser(toEntity(grantedPermission.getUser()));
        entity.setRole(toEntity(grantedPermission.getRole()));
        return entity;
    }
    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFullName(user.getFullName());
        entity.setCompanyName(user.getCompanyName());
        entity.setCountry(user.getCountry());
        entity.setPhone(user.getPhone());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setGrantedPermissions(user.getGrantedPermissions().stream()
                .map(this::toEntity)
                .collect(Collectors.toSet()));
        return entity;
    }
}
