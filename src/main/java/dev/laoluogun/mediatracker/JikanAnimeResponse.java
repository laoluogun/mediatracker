package dev.laoluogun.mediatracker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class JikanAnimeResponse {
    @JsonProperty("data")
        private List<AnimeData> data;

        public List<AnimeData> getData() { return data; }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class AnimeData {

            @JsonProperty("mal_id")
            private Long malId;

            @JsonProperty("title")
            private String title;

            @JsonProperty("synopsis")
            private String synopsis;

            @JsonProperty("episodes")
            private Integer episodes;

            @JsonProperty("images")
            private Images images;

            public Long getMalId() { return malId; }
            public String getTitle() { return title; }
            public String getSynopsis() { return synopsis; }
            public Integer getEpisodes() { return episodes; }
            public Images getImages() { return images; }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Images {

                @JsonProperty("jpg")
                private Jpg jpg;

                public Jpg getJpg() { return jpg; }

                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Jpg {

                    @JsonProperty("image_url")
                    private String imageUrl;

                    public String getImageUrl() { return imageUrl; }
                }
            }
        }
    }