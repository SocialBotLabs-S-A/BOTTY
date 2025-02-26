package application.service.security;

import application.dto.security.AuthResponse;
import application.dto.security.LoginRequest;
import application.dto.security.RegisterRequest;
import domain.model.User;
import domain.ports.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // Register a new user and verify if the email is already in use
    public AuthResponse register(RegisterRequest request) {

        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if(existing.isPresent()){
            throw new RuntimeException("Email already in use");
        }

        User newUser = new User(
                null,
                request.getFullName(),
                request.getCompanyName(),
                request.getCountry(),
                request.getPhone(),
                request.getEmail(),
                //Encrypt the password
                passwordEncoder.encode(request.getPassword())
        );

        User savedUser = userRepository.save(newUser);
        return new AuthResponse("user registered successfully");
    }
    // Login a user and verify if the user exists
    public AuthResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if(userOpt.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User user = userOpt.get();

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("invalid credentials");
        }
        return new AuthResponse("login successful");
    }
}
