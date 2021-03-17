package io.github.tadeuszsikorski.gober.controller.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class BookmarkRequest {
    @NotBlank
    private String name;
}
