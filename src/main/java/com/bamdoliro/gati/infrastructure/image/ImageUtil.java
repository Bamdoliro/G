package com.bamdoliro.gati.infrastructure.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUtil {
    String uploadImage(MultipartFile image);
}
