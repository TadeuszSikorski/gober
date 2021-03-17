package io.github.tadeuszsikorski.gober.controller.dto.response;

import io.github.tadeuszsikorski.gober.controller.dto.BookmarkDTO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BookmarksResponse {

    private List<BookmarkDTO> bookmarks;
}
