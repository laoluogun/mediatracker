package dev.laoluogun.mediatracker;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser_Id(Long userId);
    List<Review> findByMediaItem_Id(Long mediaItemId);
}
