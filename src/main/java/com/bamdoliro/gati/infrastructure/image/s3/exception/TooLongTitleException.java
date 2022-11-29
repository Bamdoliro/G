package com.bamdoliro.gati.infrastructure.image.s3.exception;

import com.bamdoliro.gati.global.error.exception.GatiException;
import com.bamdoliro.gati.infrastructure.image.s3.exception.error.ImageErrorProperty;

public class TooLongTitleException extends GatiException {

    public static final TooLongTitleException EXCEPTION = new TooLongTitleException();

    private TooLongTitleException() {
        super(ImageErrorProperty.TOO_LONG_TITLE);
    }
}
