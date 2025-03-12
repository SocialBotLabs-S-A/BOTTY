package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.rest;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.dto.security.MessageResponse;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.dto.security.RegisterRequest;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.application.service.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    /* /auth/register-user: Receives a RegisterRequest,
       invokes the authentication service to register the user,
       and returns a response.
       /auth/login: Receives a LoginRequest, validates the credentials,
       and returns a response (message or token).
    */

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest registerRequest) {
        boolean response = authService.register(registerRequest);
        if (response) {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("The user registered successfully"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Registration failed"));
        }
    }
}
