package io.github.tadeuszsikorski.gober.controller.query;

import io.github.tadeuszsikorski.gober.controller.dto.BookmarkDTO;
import io.github.tadeuszsikorski.gober.controller.dto.UserDTO;
import io.github.tadeuszsikorski.gober.controller.dto.request.BookmarkRequest;
import io.github.tadeuszsikorski.gober.controller.dto.response.BookmarksResponse;
import io.github.tadeuszsikorski.gober.logic.service.query.BookmarkQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkQueryController {

	@Autowired
	private BookmarkQueryService bookmarkQueryService;

	@GetMapping("/")
	@PreAuthorize("hasRole('USER')")
	public BookmarksResponse getBookmarks(UserDTO request) {
		return bookmarkQueryService.getBookmarks(request);
	}

    @GetMapping("/bookmark")
	@PreAuthorize("hasRole('USER')")
	public BookmarkDTO getBookmark(BookmarkRequest request) {
		return bookmarkQueryService.getBookmark(request);
	}
	



}
