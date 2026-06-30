package dev.laoluogun.mediatracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUserId(@PathVariable Long userId) {
        return reviewRepository.findByUserId(userId);
    }
}
