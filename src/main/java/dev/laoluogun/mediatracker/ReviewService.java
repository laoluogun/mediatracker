package dev.laoluogun.mediatracker;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Page<Review> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUser_Id(userId);
    }

    public List<Review> getReviewsByMediaItemId(Long mediaItemId) {
        return reviewRepository.findByMediaItem_Id(mediaItemId);
    }
}
