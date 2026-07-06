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
                .map(this::mapMangaToMediaItem)
                .collect(Collectors.toList());
    }

    private MediaItem mapMangaToMediaItem(JikanMangaResponse.MangaData mangaData) {
            String externalId = "manga-" + mangaData.getMalId();

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

    public List<MediaItem> searchAnime(String query) {
    JikanAnimeResponse response = restClient.get()
            .uri("https://api.jikan.moe/v4/anime?q={query}&limit=5", query)
            .retrieve()
            .body(JikanAnimeResponse.class);

    if (response == null || response.getData() == null) {
        return List.of();
    }

    return response.getData().stream()
            .map(this::mapAnimeToMediaItem)
            .collect(Collectors.toList());
}

private MediaItem mapAnimeToMediaItem(JikanAnimeResponse.AnimeData animeData) {
    String externalId = "anime-" + animeData.getMalId();

    return mediaItemRepository.findByExternalId(externalId)
            .orElseGet(() -> {
                MediaItem item = new MediaItem(
                        animeData.getTitle(),
                        MediaType.ANIME,
                        externalId,
                        animeData.getSynopsis(),
                        null,
                        animeData.getImages() != null &&
                        animeData.getImages().getJpg() != null
                                ? animeData.getImages().getJpg().getImageUrl()
                                : null,
                        animeData.getEpisodes()
                );
                return mediaItemRepository.save(item);
            });
}
}
