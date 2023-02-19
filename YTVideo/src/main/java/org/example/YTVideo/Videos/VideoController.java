package org.example.YTVideo.Videos;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoRepository videoRepository;
    private final YouTubeAPIService youTubeAPIService;

    public VideoController(VideoRepository videoRepository, YouTubeAPIService youTubeAPIService) {
        this.videoRepository = videoRepository;
        this.youTubeAPIService = youTubeAPIService;
    }

    @GetMapping(value="/",params = {"q","page","size"})
    public Page<Video> searchVideos(@RequestParam String q, @RequestParam int page, @RequestParam int size) throws IOException {
        Pageable pageable = PageRequest.of(page, size, Sort.by("publishedAt").descending());
        List<Video> videos = youTubeAPIService.searchVideos(q, pageable);
        videoRepository.saveAll(videos);
        return (Page<Video>) videoRepository.findByTitleContainingIgnoreCaseOrderByPublishedAtDesc(q, pageable);
    }
}
