package org.example.YTVideo.Videos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
@Entity
@Table(name = "Videos")
@Getter
@Setter
public class Video {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    
    private String title;

    private String description;

    @Column(name = "published_at")
    private Instant publishedAt;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;


}
