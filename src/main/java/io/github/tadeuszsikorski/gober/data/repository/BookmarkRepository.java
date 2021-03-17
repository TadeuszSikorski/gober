package io.github.tadeuszsikorski.gober.data.repository;

import java.util.UUID;

import io.github.tadeuszsikorski.gober.data.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, UUID> {
    
    Bookmark findByName(String name);

    Bookmark findByUrl(String url);

    Bookmark findByAddDate(String addDate);

    Boolean existsByName(String name);

    Boolean existsByUrl(String url);
}
