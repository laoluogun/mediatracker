package dev.laoluogun;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMediaEntryRepository extends JpaRepository<UserMediaEntry, Long> {
    
}
