package dev.laoluogun.mediatracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OMDbService {

    private final RestClient restClient;
    private final MediaItemRepository mediaItemRepository;

    @Value("${OMDb.api.key}")
    private String apiKey;

    public OMDbService(MediaItemRepository mediaItemRepository) {
        this.restClient = RestClient.create();
        this.mediaItemRepository = mediaItemRepository;
    }

    public List<MediaItem> searchMovies(String query) {
        OMDbMovieResponse response = restClient.get()
                .uri("https://www.OMDbapi.com/?s={query}&type=movie&apikey={apiKey}", query, apiKey)
                .retrieve()
                .body(OMDbMovieResponse.class);

        if (response == null || response.getSearch() == null) {
            return List.of();
        }

        return response.getSearch().stream()
                .map(this::mapToMediaItem)
                .collect(Collectors.toList());
    }

    private MediaItem mapToMediaItem(OMDbMovieResponse.MovieData movieData) {
        String externalId = "movie-" + movieData.getImdbId();

        return mediaItemRepository.findByExternalId(externalId)
                .orElseGet(() -> {
                    Integer releaseYear = null;
                    try {
                        releaseYear = Integer.parseInt(movieData.getYear().substring(0, 4));
                    } catch (Exception e) {

                    }

                    MediaItem item = new MediaItem(
                            movieData.getTitle(),
                            MediaType.MOVIE,
                            externalId,
                            null,
                            releaseYear,
                            "N/A".equals(movieData.getPoster()) ? null : movieData.getPoster(),
                            null
                    );
                    return mediaItemRepository.save(item);
                });
    }
}