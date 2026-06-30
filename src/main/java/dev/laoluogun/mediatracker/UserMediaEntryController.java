package dev.laoluogun.mediatracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media-entries")
public class UserMediaEntryController {
    
    private final UserMediaEntryService userMediaEntryService;

    @Autowired
    public UserMediaEntryController(UserMediaEntryService userMediaEntryService) {
        this.userMediaEntryService = userMediaEntryService;
    }

    @PostMapping
    public UserMediaEntry createUserMediaEntry(@RequestBody UserMediaEntry userMediaEntry) {
        return userMediaEntryService.createUserMediaEntry(userMediaEntry);
    }

    @GetMapping
    public List<UserMediaEntry> getAllUserMediaEntries() {
        return userMediaEntryService.getAllUserMediaEntries();
    }

    @GetMapping("/{id}")
    public UserMediaEntry getUserMediaEntryById(@PathVariable Long id) {
        return userMediaEntryService.getUserMediaEntryById(id);
    }

    @GetMapping("/user/{userId}")
    public List<UserMediaEntry> getUserMediaEntriesByUserId(@PathVariable Long userId) {
        return userMediaEntryService.getUserMediaEntriesByUserId(userId);
    }

}
