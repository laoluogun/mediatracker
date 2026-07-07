package dev.laoluogun.mediatracker;

import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class MediaItemService {
    
    
    private final MediaItemRepository mediaItemRepository;

    private final JikanService jikanService;

    private final OMDbService omdbService;

    private final GoogleBooksService googleBooksService;

    public MediaItemService(MediaItemRepository mediaItemRepository, JikanService jikanService, OMDbService omdbService, GoogleBooksService googleBooksService) {
    this.mediaItemRepository = mediaItemRepository;
    this.jikanService = jikanService;
    this.omdbService = omdbService;
    this.googleBooksService = googleBooksService;
    }

    public List<MediaItem> searchManga(String query) {
        return jikanService.searchManga(query);
    }

    public List<MediaItem> searchAnime(String query) {
        return jikanService.searchAnime(query);
    }

    public List<MediaItem> searchMovies(String query) {
        return omdbService.searchMovies(query);
    }

    public List<MediaItem> searchBooks(String query) {
        return googleBooksService.searchBooks(query);
    }

    public MediaItem createMediaItem(MediaItem mediaItem) {
        return mediaItemRepository.save(mediaItem);
    }

    public Page<MediaItem> getAllMediaItems(Pageable pageable) {
    return mediaItemRepository.findAll(pageable);
    }

    public MediaItem getMediaItemById(Long id) {
        return mediaItemRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Media item not found with id: " + id));
    }
}
