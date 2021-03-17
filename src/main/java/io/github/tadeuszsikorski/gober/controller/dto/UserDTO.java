package io.github.tadeuszsikorski.gober.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UserDTO {

    @NotBlank
    private String username;

}
