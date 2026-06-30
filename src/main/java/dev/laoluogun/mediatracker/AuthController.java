package dev.laoluogun.mediatracker;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<?> registerUser(@RequestBody AppUsers appUser) {
        if (appUsersRepository.findByUsername(appUser.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        
        AppUsers newUser = new AppUsers(
            appUser.getUsername(),
            appUser.getEmail(),
            passwordEncoder.encode(appUser.getPassword())
        );
     
        appUsersRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AppUsers appUser) {
        AppUsers user = appUsersRepository.findByUsername(appUser.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + appUser.getUsername()));

        if (!passwordEncoder.matches(appUser.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password.");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }

}
