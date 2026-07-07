package dev.laoluogun.mediatracker;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoogleBooksService {

    private final RestClient restClient;
    private final MediaItemRepository mediaItemRepository;

    public GoogleBooksService(MediaItemRepository mediaItemRepository) {
        this.restClient = RestClient.create();
        this.mediaItemRepository = mediaItemRepository;
    }

    public List<MediaItem> searchBooks(String query) {
        GoogleBooksResponse response = restClient.get()
                .uri("https://www.googleapis.com/books/v1/volumes?q={query}&maxResults=5", query)
                .retrieve()
                .body(GoogleBooksResponse.class);

        if (response == null || response.getItems() == null) {
            return List.of();
        }

        return response.getItems().stream()
                .map(this::mapToMediaItem)
                .collect(Collectors.toList());
    }

    private MediaItem mapToMediaItem(GoogleBooksResponse.BookItem bookItem) {
        String externalId = "book-" + bookItem.getId();

        return mediaItemRepository.findByExternalId(externalId)
                .orElseGet(() -> {
                    GoogleBooksResponse.BookItem.VolumeInfo info = bookItem.getVolumeInfo();

                    Integer releaseYear = null;
                    try {
                        if (info.getPublishedDate() != null) {
                            releaseYear = Integer.parseInt(info.getPublishedDate().substring(0, 4));
                        }
                    } catch (Exception e) {

                    }

                    String coverUrl = null;
                    if (info.getImageLinks() != null && 
                        info.getImageLinks().getThumbnail() != null) {
                        coverUrl = info.getImageLinks().getThumbnail();
                    }

                    MediaItem item = new MediaItem(
                            info.getTitle(),
                            MediaType.BOOK,
                            externalId,
                            info.getDescription(),
                            releaseYear,
                            coverUrl,
                            info.getPageCount()
                    );
                    return mediaItemRepository.save(item);
                });
    }
}