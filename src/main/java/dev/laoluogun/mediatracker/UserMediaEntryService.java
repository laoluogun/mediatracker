package dev.laoluogun.mediatracker;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserMediaEntryService {
    private final UserMediaEntryRepository userMediaEntryRepository;

    public UserMediaEntryService(UserMediaEntryRepository userMediaEntryRepository) {
        this.userMediaEntryRepository = userMediaEntryRepository;
    }

    public UserMediaEntry createUserMediaEntry(UserMediaEntry userMediaEntry) {
        return userMediaEntryRepository.save(userMediaEntry);
    }

    public Page<UserMediaEntry> getAllUserMediaEntries(Pageable pageable) {
        return userMediaEntryRepository.findAll(pageable);
    }

    public UserMediaEntry getUserMediaEntryById(Long id) {
        return userMediaEntryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User media entry not found with id: " + id));
    }

    public List<UserMediaEntry> getUserMediaEntriesByUserId(Long userId) {
        return userMediaEntryRepository.findByUserId(userId);
    }
    
}
