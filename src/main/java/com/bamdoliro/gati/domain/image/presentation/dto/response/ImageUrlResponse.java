package com.bamdoliro.gati.domain.image.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ImageUrlResponse {
    private final List<String> imgUrl;
}
