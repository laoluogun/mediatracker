package dev.laoluogun.mediatracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUsersController {

    private final AppUsersService appUsersService;

    @Autowired
    public AppUsersController(AppUsersService appUsersService) {
        this.appUsersService = appUsersService;
    }

    @PostMapping
    public AppUsers createAppUser(@RequestBody AppUsers appUser) {
        return appUsersService.createAppUser(appUser);
    }

    @GetMapping
    public List<AppUsers> getAllAppUsers() {
        return appUsersService.getAllAppUsers();
    }

    @GetMapping("/{id}")
    public AppUsers getAppUserById(@PathVariable Long id) {
        return appUsersService.getAppUserById(id);
    }
}