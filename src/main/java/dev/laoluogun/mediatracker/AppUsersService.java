package dev.laoluogun.mediatracker;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class AppUsersService {
    
    private final AppUsersRepository appUsersRepository;

    @Autowired
    public AppUsersService(AppUsersRepository appUsersRepository) {
        this.appUsersRepository = appUsersRepository;
    }

    public AppUsers createAppUser(AppUsers appUser) {
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