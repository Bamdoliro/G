package com.bamdoliro.gati.domain.image.presentation;

import com.bamdoliro.gati.domain.image.presentation.dto.response.ImageUrlResponse;
import com.bamdoliro.gati.domain.image.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageUploadService imageUploadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ImageUrlResponse saveImage(@RequestPart List<MultipartFile> images) {
        return imageUploadService.execute(images);
    }
}
