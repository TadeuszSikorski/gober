package io.github.tadeuszsikorski.gober.controller.dto.response;

import io.github.tadeuszsikorski.gober.controller.dto.TagDTO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TagsResponse {

    private List<TagDTO> tags;
}
