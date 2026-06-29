package dev.laoluogun.mediatracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaItemTestController {

    private final MediaItemRepository mediaItemRepository;

    @Autowired
    public MediaItemTestController(MediaItemRepository mediaItemRepository) {
        this.mediaItemRepository = mediaItemRepository;
    }

    @PostMapping
    public MediaItem createMediaItem(@RequestBody MediaItem mediaItem) {
        return mediaItemRepository.save(mediaItem);
    }

    @GetMapping
    public List<MediaItem> getAllMediaItems() {
        return mediaItemRepository.findAll();
    }
}