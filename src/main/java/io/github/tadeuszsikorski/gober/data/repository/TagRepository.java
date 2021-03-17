package io.github.tadeuszsikorski.gober.data.repository;

import java.util.UUID;

import io.github.tadeuszsikorski.gober.data.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    
    Tag findByName(String name);

    Boolean existsByName(String name);
}
