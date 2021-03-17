package io.github.tadeuszsikorski.gober.logic.service.query;

import io.github.tadeuszsikorski.gober.controller.dto.TagDTO;
import io.github.tadeuszsikorski.gober.controller.dto.UserDTO;
import io.github.tadeuszsikorski.gober.data.entity.Tag;
import io.github.tadeuszsikorski.gober.data.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.tadeuszsikorski.gober.controller.dto.BookmarkDTO;
import io.github.tadeuszsikorski.gober.controller.dto.response.BookmarksResponse;
import io.github.tadeuszsikorski.gober.data.repository.TagRepository;
import io.github.tadeuszsikorski.gober.controller.dto.response.TagResponse;
import io.github.tadeuszsikorski.gober.controller.dto.response.TagsResponse;
import io.github.tadeuszsikorski.gober.logic.util.ServiceUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagQueryService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public TagResponse getTag(TagDTO request) {
        Tag tag = tagRepository.findByName(request.getName());

        return new TagResponse(tag.getName(), ServiceUtils.getBookmarks(tag.getBookmarks()));
    }

    public TagsResponse getTags(UserDTO request) {
        List<TagDTO> tags = tagRepository.findAll()
                .stream()
                .filter(tag -> tag.getUser().getUsername() == request.getUsername())
                .map(tag -> new TagDTO(
                        tag.getName(),
                        new UserDTO(tag.getUser().getUsername())))
                .collect(Collectors.toList());

        return new TagsResponse(tags);
    }

    public BookmarksResponse getBookmarksForSpecificTag(TagDTO request) {
        List<BookmarkDTO> bookmarks = bookmarkRepository.findAll().stream()
                .filter(bookmark -> bookmark.getTags().stream()
                        .anyMatch(tag -> tag.getName() == request.getName()))
                .map(bookmark -> new BookmarkDTO(
                        bookmark.getName(),
                        bookmark.getUrl(),
                        bookmark.getAddDate(),
                        ServiceUtils.getTags(bookmark.getTags()),
                        new UserDTO(bookmark.getUser().getUsername())))
                .collect(Collectors.toList());

        return new BookmarksResponse(bookmarks);
    }
}
