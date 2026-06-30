package dev.laoluogun.mediatracker;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class AppUsersService {
    
    private final AppUsersRepository appUsersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUsersService(AppUsersRepository appUsersRepository, PasswordEncoder passwordEncoder) {
        this.appUsersRepository = appUsersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUsers createAppUser(AppUsers appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUsersRepository.save(appUser);
    }

    public List<AppUsers> getAllAppUsers() {
        return appUsersRepository.findAll();
    }

    public AppUsers getAppUserById(Long id) {
        return appUsersRepository.findById(id).
            orElseThrow(() -> new RuntimeException("App user not found with id: " + id));
    }
}