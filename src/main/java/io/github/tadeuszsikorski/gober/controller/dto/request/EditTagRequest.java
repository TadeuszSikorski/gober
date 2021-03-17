package io.github.tadeuszsikorski.gober.controller.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class EditTagRequest {

    @NotBlank
    private String oldName;

    @NotBlank
    private String newName;

}
