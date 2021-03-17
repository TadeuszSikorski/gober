package io.github.tadeuszsikorski.gober.data.entity;

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
@Table(name = "tags", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "user_id"})
})
@NoArgsConstructor
public class Tag {
    
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

    @ManyToMany(mappedBy = "tags")
    private Set<Bookmark> bookmarks;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Tag(String name) {
        this.name = name;
    }
}
