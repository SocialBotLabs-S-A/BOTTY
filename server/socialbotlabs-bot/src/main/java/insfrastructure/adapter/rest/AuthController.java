package insfrastructure.adapter.rest;
import application.dto.security.AuthResponse;
import application.dto.security.LoginRequest;
import application.dto.security.RegisterRequest;
import application.service.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    ///v1/auth/register: Receives a RegisterRequest,
    ///invokes the authentication service to register the user,
    /// and returns a response.
    ///v1/auth/login: Receives a LoginRequest, validates the credentials,
    /// and returns a response (message or token).
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
