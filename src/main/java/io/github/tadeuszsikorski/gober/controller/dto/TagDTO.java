package io.github.tadeuszsikorski.gober.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagDTO {

    @NotBlank
    private String name;

    @NotEmpty
    private UserDTO user;
}
