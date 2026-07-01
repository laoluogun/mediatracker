package dev.laoluogun.mediatracker;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/media")
public class MediaItemController {

    private final MediaItemService mediaItemService;

    public MediaItemController(MediaItemService mediaItemService) {
        this.mediaItemService = mediaItemService;
    }

    @PostMapping
    public MediaItem createMediaItem(@RequestBody MediaItem mediaItem) {
        return mediaItemService.createMediaItem(mediaItem);
    }

    @GetMapping
    public Page<MediaItem> getAllMediaItems(Pageable pageable) {
        return mediaItemService.getAllMediaItems(pageable);
    }

    @GetMapping("/{id}")
    public MediaItem getMediaItemById(@PathVariable Long id) {
        return mediaItemService.getMediaItemById(id);
    }

    @GetMapping("/search")
    public List<MediaItem> searchManga(@RequestParam String query) {
        return mediaItemService.searchManga(query);
    }

}

