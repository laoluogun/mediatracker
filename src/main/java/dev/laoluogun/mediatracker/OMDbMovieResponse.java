package dev.laoluogun.mediatracker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OMDbMovieResponse {

    @JsonProperty("Search")
    private List<MovieData> search;

    public List<MovieData> getSearch() { return search; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieData {

        @JsonProperty("imdbID")
        private String imdbId;

        @JsonProperty("Title")
        private String title;

        @JsonProperty("Year")
        private String year;

        @JsonProperty("Poster")
        private String poster;

        public String getImdbId() { return imdbId; }
        public String getTitle() { return title; }
        public String getYear() { return year; }
        public String getPoster() { return poster; }
    }
}