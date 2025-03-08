package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.service.security;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.dto.security.RegisterRequest;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.Role;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.persistence.role.RoleRepositoryAdapter;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.User;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.persistence.UserRepositoryAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Value("${social.botty.default.role}")
    private String defaultRole;

    private final UserRepositoryAdapter userRepositoryAdapter;
    private final RoleRepositoryAdapter roleRepositoryAdapter;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepositoryAdapter userRepositoryAdapter,
                       RoleRepositoryAdapter roleRepositoryAdapter,
                       PasswordEncoder passwordEncoder) {
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.passwordEncoder = passwordEncoder;
        this.roleRepositoryAdapter = roleRepositoryAdapter;
    }


    public boolean register(RegisterRequest request) {
        // 1. Check if the email is already registered / Verificar si el email ya está registrado
        Optional<User> existingUser = userRepositoryAdapter.findByEmail(request.getEmail());
        if (existingUser.isPresent() && !validatedPassword(request)) {
            return false;
        }

        // 2. Create a new user with the provided information / Crear un nuevo usuario con la información proporcionada
        User user = new User(null,
            request.getFullName(),
            request.getUsername(),
            request.getCompanyName(),
            request.getCountry(),
            request.getPhone(),
            request.getEmail(),
            passwordEncoder.encode(request.getPassword()));

        // 3. Assign the 'user' role to the new user / Asignar el rol 'user' al nuevo usuario

        Optional<Role> roleDefault = roleRepositoryAdapter.findByRoleName(defaultRole);
        if(roleDefault.isPresent()){
            user.setRole(roleDefault.get());
        }else{
            return false;
        }

        // 4. Save the new user to the database / Guardar el nuevo usuario en la base de datos
        return userRepositoryAdapter.saveUser(user);

    }

    private boolean validatedPassword(RegisterRequest registerRequest){
        return registerRequest.getPassword().equals(registerRequest.getRepeatPassword());
    }
}
