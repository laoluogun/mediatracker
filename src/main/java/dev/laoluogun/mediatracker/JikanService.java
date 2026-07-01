package dev.laoluogun.mediatracker;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JikanService {

    private final RestClient restClient;
    private final MediaItemRepository mediaItemRepository;

    public JikanService(MediaItemRepository mediaItemRepository) {
        this.restClient = RestClient.create();
        this.mediaItemRepository = mediaItemRepository;
    }

    public List<MediaItem> searchManga(String query) {
        JikanMangaResponse response = restClient.get()
                .uri("https://api.jikan.moe/v4/manga?q={query}&limit=5", query)
                .retrieve()
                .body(JikanMangaResponse.class);

        if (response == null || response.getData() == null) {
            return List.of();
        }

        return response.getData().stream()
                .map(this::mapToMediaItem)
                .collect(Collectors.toList());
    }

    private MediaItem mapToMediaItem(JikanMangaResponse.MangaData mangaData) {
        String externalId = String.valueOf(mangaData.getMalId());

        return mediaItemRepository.findByExternalId(externalId)
                .orElseGet(() -> {
                    MediaItem item = new MediaItem(
                            mangaData.getTitle(),
                            MediaType.MANGA,
                            externalId,
                            mangaData.getSynopsis(),
                            null,
                            mangaData.getImages() != null &&
                            mangaData.getImages().getJpg() != null
                                    ? mangaData.getImages().getJpg().getImageUrl()
                                    : null,
                            mangaData.getChapters()
                    );
                    return mediaItemRepository.save(item);
                });
    }
}
