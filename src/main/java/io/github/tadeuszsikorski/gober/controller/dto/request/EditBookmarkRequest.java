package io.github.tadeuszsikorski.gober.controller.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import io.github.tadeuszsikorski.gober.controller.dto.TagDTO;

@Getter
public class EditBookmarkRequest {

    @NotBlank
    private String oldName;

    @NotBlank
    private String newName;

    @NotBlank
    private String url;

    @NotBlank
    private String addDate;

    @NotEmpty
	private List<TagDTO> tags;
}