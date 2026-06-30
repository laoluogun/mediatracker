package dev.laoluogun.mediatracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserTestController {

    private final AppUsersRepository appUsersRepository;

    @Autowired
    public UserTestController(AppUsersRepository appUsersRepository) {
        this.appUsersRepository = appUsersRepository;
    }

    @PostMapping
    public AppUsers createUser(@RequestBody AppUsers appUsers) {
        return appUsersRepository.save(appUsers);
    }

    @GetMapping
    public List<AppUsers> getAllUsers() {
        return appUsersRepository.findAll();
    }
}