package dev.laoluogun.mediatracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUsersRepository extends JpaRepository<AppUsers, Long> {
    java.util.Optional<AppUsers> findByUsername(String username);
    java.util.Optional<AppUsers> findByEmail(String email);
}

