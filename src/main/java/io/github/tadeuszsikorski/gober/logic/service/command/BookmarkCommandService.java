package io.github.tadeuszsikorski.gober.logic.service.command;

import io.github.tadeuszsikorski.gober.controller.dto.response.MessageResponse;
import io.github.tadeuszsikorski.gober.data.entity.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.tadeuszsikorski.gober.controller.dto.BookmarkDTO;
import io.github.tadeuszsikorski.gober.data.repository.BookmarkRepository;
import io.github.tadeuszsikorski.gober.data.repository.user.UserRepository;
import io.github.tadeuszsikorski.gober.logic.util.ServiceUtils;
import io.github.tadeuszsikorski.gober.controller.dto.request.BookmarkRequest;
import io.github.tadeuszsikorski.gober.controller.dto.request.EditBookmarkRequest;

import java.util.HashSet;

@Service
public class BookmarkCommandService {
    
    @Autowired
	private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    public MessageResponse addBookmark(BookmarkDTO request) {
        if(!bookmarkRepository.existsByName(request.getName())
        || !bookmarkRepository.existsByUrl(request.getUrl())) {
            Bookmark bookmark = new Bookmark(
                    request.getName(),
                    request.getUrl(),
                    request.getAddDate(),
                    ServiceUtils.getTags(request.getTags()),
                    userRepository.findByUsername(request.getUser().getUsername()));

            bookmarkRepository.save(bookmark);

            return new MessageResponse("Zakładka została dodana.");
        }

        return new MessageResponse("Zakładka jest już w bazie.");
    }

    public MessageResponse editBookmark(EditBookmarkRequest request) {
        if(bookmarkRepository.existsByName(request.getOldName())) {
            Bookmark bookmark = bookmarkRepository.findByName(request.getOldName());

            bookmark.setName(request.getNewName());
            bookmark.setUrl(request.getUrl());
            bookmark.setAddDate(request.getAddDate());
            bookmark.setTags(new HashSet<>(ServiceUtils.getTags(request.getTags())));

            bookmarkRepository.delete(bookmarkRepository.findByName(request.getOldName()));
            bookmarkRepository.save(bookmark);

            return new MessageResponse("Zakładka została zaktualizowana.");
        }

        return new MessageResponse("Nie ma takiej zakładki.");
    }

    public MessageResponse removeBookmark(BookmarkRequest request) {
        if(bookmarkRepository.existsByName(request.getName())){
            Bookmark bookmark = bookmarkRepository.findByName(request.getName());

            bookmarkRepository.delete(bookmark);

            return new MessageResponse("Zakładka została usunięta");
        }

        return new MessageResponse("Podana zakładka nie została znaleziona.");
    }
}
