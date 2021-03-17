package io.github.tadeuszsikorski.gober.logic.service.query;

import io.github.tadeuszsikorski.gober.controller.dto.UserDTO;
import io.github.tadeuszsikorski.gober.data.entity.Bookmark;
import io.github.tadeuszsikorski.gober.data.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.tadeuszsikorski.gober.controller.dto.BookmarkDTO;
import io.github.tadeuszsikorski.gober.controller.dto.request.BookmarkRequest;
import io.github.tadeuszsikorski.gober.controller.dto.response.BookmarksResponse;
import io.github.tadeuszsikorski.gober.logic.util.ServiceUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookmarkQueryService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public BookmarkDTO getBookmark(BookmarkRequest request) {
        if (bookmarkRepository.existsByName(request.getName())) {
            Bookmark bookmark = bookmarkRepository.findByName(request.getName());

            return new BookmarkDTO(
                    bookmark.getName(),
                    bookmark.getUrl(),
                    bookmark.getAddDate(),
                    ServiceUtils.getTags(bookmark.getTags()),
                    new UserDTO(bookmark.getUser().getUsername()));
        }

        return null;
    }

    public BookmarksResponse getBookmarks(UserDTO request) {
        List<BookmarkDTO> bookmarks = bookmarkRepository.findAll()
                .stream()
                .filter(bookmark -> bookmark.getUser().getUsername() == request.getUsername())
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
