package dev.laoluogun.mediatracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMediaEntryRepository extends JpaRepository<UserMediaEntry, Long> {
    
}
