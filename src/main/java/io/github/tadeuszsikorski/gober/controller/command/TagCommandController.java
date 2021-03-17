package io.github.tadeuszsikorski.gober.controller.command;

import io.github.tadeuszsikorski.gober.controller.dto.TagDTO;
import io.github.tadeuszsikorski.gober.controller.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.tadeuszsikorski.gober.controller.dto.request.EditTagRequest;
import io.github.tadeuszsikorski.gober.logic.service.command.TagCommandService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tag")
public class TagCommandController {

    @Autowired
    private TagCommandService tagCommandService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse addTag(TagDTO request) {
        return tagCommandService.addTag(request);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse editTag(EditTagRequest request){
        return tagCommandService.editTag(request);
    }

    @PostMapping("/remove")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse removeTag(TagDTO request) {
        return tagCommandService.removeTag(request);
    }
}
