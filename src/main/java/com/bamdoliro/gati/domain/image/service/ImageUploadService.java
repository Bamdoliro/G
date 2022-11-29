package com.bamdoliro.gati.domain.image.service;

import com.bamdoliro.gati.domain.image.presentation.dto.response.ImageUrlResponse;
import com.bamdoliro.gati.infrastructure.image.s3.facade.S3Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ImageUploadService {

    private final S3Facade s3Facade;

    public ImageUrlResponse execute(List<MultipartFile> images) {
        List<String> imageUrl = images.stream()
            .map(s3Facade::uploadImage)
            .collect(Collectors.toList());
        return new ImageUrlResponse(imageUrl);
    }
}
