package dev.laoluogun.mediatracker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JikanMangaResponse {

    @JsonProperty("data")
    private List<MangaData> data;

    public List<MangaData> getData() { return data; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MangaData {

        @JsonProperty("mal_id")
        private Long malId;

        @JsonProperty("title")
        private String title;

        @JsonProperty("synopsis")
        private String synopsis;

        @JsonProperty("chapters")
        private Integer chapters;

        @JsonProperty("images")
        private Images images;

        public Long getMalId() { return malId; }
        public String getTitle() { return title; }
        public String getSynopsis() { return synopsis; }
        public Integer getChapters() { return chapters; }
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