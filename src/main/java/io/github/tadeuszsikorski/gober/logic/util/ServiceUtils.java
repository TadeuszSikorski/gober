package io.github.tadeuszsikorski.gober.logic.util;

import io.github.tadeuszsikorski.gober.controller.dto.TagDTO;
import io.github.tadeuszsikorski.gober.controller.dto.UserDTO;
import io.github.tadeuszsikorski.gober.data.entity.Bookmark;
import io.github.tadeuszsikorski.gober.data.entity.Tag;
import io.github.tadeuszsikorski.gober.controller.dto.BookmarkDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class ServiceUtils {
    public static List<Tag> getTags(List<TagDTO> tags) {

        return tags.stream()
                .map(tag -> new Tag(tag.getName()))
                .collect(Collectors.toList());
    }

    public static List<BookmarkDTO> getBookmarks(Set<Bookmark> bookmarks){

        return bookmarks.stream()
                .map(bookmark -> new BookmarkDTO(
                        bookmark.getName(),
                        bookmark.getUrl(),
                        bookmark.getAddDate(),
                        ServiceUtils.getTags(bookmark.getTags()),
                        new UserDTO(bookmark.getUser().getUsername())))
                .collect(Collectors.toList());
    }

    public static List<TagDTO> getTags(Set<Tag> tags) {

        return tags.stream()
                .map(tag -> new TagDTO(tag.getName(), new UserDTO(tag.getUser().getUsername())))
                .collect(Collectors.toList());
    }

}
