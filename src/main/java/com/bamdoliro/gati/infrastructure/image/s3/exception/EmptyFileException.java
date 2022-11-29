package com.bamdoliro.gati.infrastructure.image.s3.exception;

import com.bamdoliro.gati.global.error.exception.GatiException;
import com.bamdoliro.gati.infrastructure.image.s3.exception.error.ImageErrorProperty;

public class EmptyFileException extends GatiException {

    public static final EmptyFileException EXCEPTION = new EmptyFileException();

    private EmptyFileException() {
        super(ImageErrorProperty.EMPTY_FILE);
    }
}
