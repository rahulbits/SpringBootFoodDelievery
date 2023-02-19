package org.example.YTVideo.Videos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
    List<Video> findByTitleContainingIgnoreCaseOrderByPublishedAtDesc(String title, Pageable pageable);
}