package dev.laoluogun.mediatracker;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class MediaItemService {
    
    private final MediaItemRepository mediaItemRepository;

    @Autowired
    public MediaItemService(MediaItemRepository mediaItemRepository) {
        this.mediaItemRepository = mediaItemRepository;
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
