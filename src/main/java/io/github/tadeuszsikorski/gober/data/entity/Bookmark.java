package io.github.tadeuszsikorski.gober.data.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.tadeuszsikorski.gober.data.entity.user.User;

@Data
@Entity
@Table(name = "bookmarks", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "url", "user_id"})
})
@NoArgsConstructor
public class Bookmark {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @NotNull
    private String addDate;

    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "bookmark_tags", 
				joinColumns = @JoinColumn(name = "bookmark_id"), 
				inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Bookmark(String name, String url, String addDate, List<Tag> tags, User user) {
        this.name = name;
        this.url = url;
        this.addDate = addDate;
        this.tags = new HashSet<>(tags);
        this.user = user;
    }
}
