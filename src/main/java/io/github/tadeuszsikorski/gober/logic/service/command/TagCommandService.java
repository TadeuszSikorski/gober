package io.github.tadeuszsikorski.gober.logic.service.command;

import io.github.tadeuszsikorski.gober.controller.dto.TagDTO;
import io.github.tadeuszsikorski.gober.controller.dto.request.EditTagRequest;
import io.github.tadeuszsikorski.gober.controller.dto.response.MessageResponse;
import io.github.tadeuszsikorski.gober.data.entity.Tag;
import io.github.tadeuszsikorski.gober.data.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.tadeuszsikorski.gober.data.repository.user.UserRepository;

@Service
public class TagCommandService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    public MessageResponse addTag(TagDTO request) {
        if (!tagRepository.existsByName(request.getName())) {
            Tag tag = new Tag(request.getName());

            tag.setUser(userRepository.findByUsername(request.getUser().getUsername()));

            tagRepository.save(tag);

            return new MessageResponse("Tag został dodany.");
        }

        return new MessageResponse("Podany tag już istnieje.");
    }

    public MessageResponse editTag(EditTagRequest request) {
        if(tagRepository.existsByName(request.getOldName())) {
            Tag tag = tagRepository.findByName(request.getOldName());

            tag.setName(request.getNewName());

            tagRepository.save(tag);

            return new MessageResponse("Zakładka została zaktualizowana.");
        }

        return new MessageResponse("Nie ma takiej zakładki.");
    }

    public MessageResponse removeTag(TagDTO request) {
        if(tagRepository.existsByName(request.getName())){
            Tag tag = tagRepository.findByName(request.getName());

            tagRepository.delete(tag);

            return new MessageResponse("Tag został usunięty");
        }

        return new MessageResponse("Podany tag nie został znaleziony.");
    }
}
