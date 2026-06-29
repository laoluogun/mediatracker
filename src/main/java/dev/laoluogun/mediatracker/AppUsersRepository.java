package dev.laoluogun.mediatracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUsersRepository extends JpaRepository<AppUsers, Long> {
    
}
