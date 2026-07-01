package dev.laoluogun.mediatracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaItemRepository extends JpaRepository<MediaItem, Long> {
    java.util.Optional<MediaItem> findByExternalId(String externalId);
}
