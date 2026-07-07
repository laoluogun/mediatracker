package dev.laoluogun.mediatracker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksResponse {

    @JsonProperty("items")
    private List<BookItem> items;

    public List<BookItem> getItems() { return items; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BookItem {

        @JsonProperty("id")
        private String id;

        @JsonProperty("volumeInfo")
        private VolumeInfo volumeInfo;

        public String getId() { return id; }
        public VolumeInfo getVolumeInfo() { return volumeInfo; }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class VolumeInfo {

            @JsonProperty("title")
            private String title;

            @JsonProperty("description")
            private String description;

            @JsonProperty("publishedDate")
            private String publishedDate;

            @JsonProperty("pageCount")
            private Integer pageCount;

            @JsonProperty("imageLinks")
            private ImageLinks imageLinks;

            public String getTitle() { return title; }
            public String getDescription() { return description; }
            public String getPublishedDate() { return publishedDate; }
            public Integer getPageCount() { return pageCount; }
            public ImageLinks getImageLinks() { return imageLinks; }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class ImageLinks {

                @JsonProperty("thumbnail")
                private String thumbnail;

                public String getThumbnail() { return thumbnail; }
            }
        }
    }
}