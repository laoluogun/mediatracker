package dev.laoluogun.mediatracker;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;


import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AppUsersRepository appUsersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AppUsersRepository appUsersRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.appUsersRepository = appUsersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        if (appUsersRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        if (appUsersRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        AppUsers newUser = new AppUsers(
            request.getUsername(),
            request.getEmail(),
            passwordEncoder.encode(request.getPassword())
        );
        appUsersRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AppUsers appUser) {
        AppUsers user = appUsersRepository.findByUsername(appUser.getUsername())
                .orElse(null);

        if (user == null || !passwordEncoder.matches(appUser.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password.");
        }
        

        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }

}
