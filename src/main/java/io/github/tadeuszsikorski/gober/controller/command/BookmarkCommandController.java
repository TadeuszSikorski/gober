package io.github.tadeuszsikorski.gober.controller.command;

import io.github.tadeuszsikorski.gober.controller.dto.request.EditBookmarkRequest;
import io.github.tadeuszsikorski.gober.controller.dto.response.MessageResponse;
import io.github.tadeuszsikorski.gober.logic.service.command.BookmarkCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.tadeuszsikorski.gober.controller.dto.BookmarkDTO;
import io.github.tadeuszsikorski.gober.controller.dto.request.BookmarkRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bookmark")
public class BookmarkCommandController {

    @Autowired
    private BookmarkCommandService bookmarkCommandService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse addBookmark(BookmarkDTO request) {
        return bookmarkCommandService.addBookmark(request);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse editBookmark(EditBookmarkRequest request) {
        return bookmarkCommandService.editBookmark(request);
    }

    @PostMapping("/remove")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse removeBookmark(BookmarkRequest request) {
        return bookmarkCommandService.removeBookmark(request);
    }

}
