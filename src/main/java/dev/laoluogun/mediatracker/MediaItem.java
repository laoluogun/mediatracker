package dev.laoluogun.mediatracker;

import jakarta.persistence.*;

@Entity
@Table(name = "media_items")
public class MediaItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType mediaType;

    private String externalId;

    @Column(length = 2000)
    private String description;

    private Integer releaseYear;

    private String coverImageUrl;

    private Integer totalEpisodesOrPages;

    // Constructors, getters, and setters

    public MediaItem() {
    }

    public MediaItem(String title, MediaType mediaType, String externalId, String description, Integer releaseYear, String coverImageUrl, Integer totalEpisodesOrPages) {
        this.title = title;
        this.mediaType = mediaType;
        this.externalId = externalId;
        this.description = description;
        this.releaseYear = releaseYear;
        this.coverImageUrl = coverImageUrl;
        this.totalEpisodesOrPages = totalEpisodesOrPages;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Integer getTotalEpisodesOrPages() {
        return totalEpisodesOrPages;
    }

    public void setTotalEpisodesOrPages(Integer totalEpisodesOrPages) {
        this.totalEpisodesOrPages = totalEpisodesOrPages;
    }

    
}