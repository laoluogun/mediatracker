package dev.laoluogun;

import dev.laoluogun.mediatracker.AppUsers;
import dev.laoluogun.mediatracker.MediaItem;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_media_entries")
public class UserMediaEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUsers user;

    @ManyToOne
    @JoinColumn(name = "media_item_id", nullable = false)
    private MediaItem mediaItem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaStatus status; // e.g., "Watching", "Completed", "On Hold", etc.

    private Integer progress; // e.g., number of episodes watched or pages read or chapters read etc.

    private Integer rating; // e.g., 1-10 scale

    private LocalDateTime updatedAt;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Constructors, getters, and setters

    public UserMediaEntry() {
    }

    public UserMediaEntry(AppUsers user, MediaItem mediaItem, MediaStatus status, Integer progress, Integer rating) {
        this.user = user;
        this.mediaItem = mediaItem;
        this.status = status;
        this.progress = progress;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public AppUsers getUser() {
        return user;
    }

    public void setUser(AppUsers user) {
        this.user = user;
    }

    public MediaItem getMediaItem() {
        return mediaItem;
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    public MediaStatus getStatus() {
        return status;
    }
    
    public void setStatus(MediaStatus status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
    
}