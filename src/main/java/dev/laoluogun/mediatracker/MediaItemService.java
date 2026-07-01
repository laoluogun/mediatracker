package dev.laoluogun.mediatracker;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MediaItemService {
    
    
    private final MediaItemRepository mediaItemRepository;

    private final JikanService jikanService;

    public MediaItemService(MediaItemRepository mediaItemRepository, JikanService jikanService) {
    this.mediaItemRepository = mediaItemRepository;
    this.jikanService = jikanService;
    }

    public List<MediaItem> searchManga(String query) {
        return jikanService.searchManga(query);
    }

    public MediaItem createMediaItem(MediaItem mediaItem) {
        return mediaItemRepository.save(mediaItem);
    }

    public List<MediaItem> getAllMediaItems() {
        return mediaItemRepository.findAll();
    }

    public MediaItem getMediaItemById(Long id) {
        return mediaItemRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Media item not found with id: " + id));
    }
}
