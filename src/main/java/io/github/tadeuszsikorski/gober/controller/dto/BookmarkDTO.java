package io.github.tadeuszsikorski.gober.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookmarkDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String url;

    @NotBlank
    private String addDate;

    @NotEmpty
    private List<TagDTO> tags;

    @NotEmpty
    private UserDTO user;
}
