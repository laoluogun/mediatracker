package dev.laoluogun.mediatracker;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserMediaEntryRepository extends JpaRepository<UserMediaEntry, Long> {
    List<UserMediaEntry> findByUserId(Long userId);
}

