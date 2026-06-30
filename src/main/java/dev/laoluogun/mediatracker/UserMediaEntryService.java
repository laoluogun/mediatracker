package dev.laoluogun.mediatracker;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserMediaEntryService {
    private final UserMediaEntryRepository userMediaEntryRepository;

    @Autowired
    public UserMediaEntryService(UserMediaEntryRepository userMediaEntryRepository) {
        this.userMediaEntryRepository = userMediaEntryRepository;
    }

    public UserMediaEntry createUserMediaEntry(UserMediaEntry userMediaEntry) {
        return userMediaEntryRepository.save(userMediaEntry);
    }

    public List<UserMediaEntry> getAllUserMediaEntries() {
        return userMediaEntryRepository.findAll();
    }

    public UserMediaEntry getUserMediaEntryById(Long id) {
        return userMediaEntryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User media entry not found with id: " + id));
    }

    public List<UserMediaEntry> getUserMediaEntriesByUserId(Long userId) {
        return userMediaEntryRepository.findByUserId(userId);
    }
    
}
