package dev.laoluogun.mediatracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media-entries")
public class UserMediaEntryTestController {
    

    private final UserMediaEntryRepository userMediaEntryRepository;

    @Autowired
    public UserMediaEntryTestController(UserMediaEntryRepository userMediaEntryRepository) {
        this.userMediaEntryRepository = userMediaEntryRepository;
    }

    @PostMapping
    public UserMediaEntry createUserMediaEntry(@RequestBody UserMediaEntry userMediaEntry) {
        return userMediaEntryRepository.save(userMediaEntry);
    }

    @GetMapping
    public List<UserMediaEntry> getAllUserMediaEntries() {
        return userMediaEntryRepository.findAll();
    }
}
