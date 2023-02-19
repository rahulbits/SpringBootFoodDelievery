package org.example.YTVideo.Videos;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class YouTubeAPIService {
    private static final String API_KEY = "AIzaSyBahPNAhrXBuNjEHc6z6WAYvhH9q7IEkJA";
    private static final String APPLICATION_NAME = "ytpro-378213";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private final YouTube youtube;

    public YouTubeAPIService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        HttpRequestInitializer httpRequestInitializer = request -> {
        };
        youtube = new YouTube.Builder(httpTransport, JSON_FACTORY, httpRequestInitializer)
                .setApplicationName(APPLICATION_NAME).build();
    }

    public List<Video> searchVideos(String query, Pageable pageable) throws IOException {
        List<Video> videos = new ArrayList<>();

        YouTube.Search.List searchList = youtube.search().list("id,snippet")
                .setKey(API_KEY)
                .setQ(query)
                .setType("video")
                .setMaxResults((long) pageable.getPageSize())
                .setPageToken(String.valueOf(pageable.getPageNumber() + 1));
        SearchListResponse searchListResponse = searchList.execute();
        List<SearchResult> searchResults = searchListResponse.getItems();

        for (SearchResult searchResult : searchResults) {
            Video video = new Video();
            video.setId(searchResult.getId().getVideoId());
            video.setTitle(searchResult.getSnippet().getTitle());
            video.setDescription(searchResult.getSnippet().getDescription());
            video.setPublishedAt(Instant.parse(searchResult.getSnippet().getPublishedAt().toString()));
            video.setThumbnailUrl(searchResult.getSnippet().getThumbnails().getHigh().getUrl());
            videos.add(video);
        }

        return videos;
    }
}
