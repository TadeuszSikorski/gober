package io.github.tadeuszsikorski.gober.controller.query;

import io.github.tadeuszsikorski.gober.controller.dto.TagDTO;
import io.github.tadeuszsikorski.gober.controller.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.tadeuszsikorski.gober.controller.dto.response.BookmarksResponse;
import io.github.tadeuszsikorski.gober.controller.dto.response.TagResponse;
import io.github.tadeuszsikorski.gober.controller.dto.response.TagsResponse;
import io.github.tadeuszsikorski.gober.logic.service.query.TagQueryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tags")
public class TagQueryController {

    @Autowired
    private TagQueryService tagQueryService;

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public TagsResponse getAllTags(UserDTO request) {
        return tagQueryService.getTags(request);
    }

    @GetMapping("/tag")
    @PreAuthorize("hasRole('USER')")
    public TagResponse getTag(TagDTO request) {
        return tagQueryService.getTag(request);
    }

    @GetMapping("/bookmarks")
    @PreAuthorize("hasRole('USER')")
    public BookmarksResponse getBookmarksForSpecificTag(TagDTO request)  {
        return tagQueryService.getBookmarksForSpecificTag(request);
    }
}
