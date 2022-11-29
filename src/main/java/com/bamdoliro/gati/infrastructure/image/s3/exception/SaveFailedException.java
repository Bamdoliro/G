package com.bamdoliro.gati.infrastructure.image.s3.exception;

import com.bamdoliro.gati.global.error.exception.GatiException;
import com.bamdoliro.gati.infrastructure.image.s3.exception.error.ImageErrorProperty;

public class SaveFailedException extends GatiException {

    public final static SaveFailedException EXCEPTION = new SaveFailedException();

    private SaveFailedException() {
        super(ImageErrorProperty.FAILED_SAVE_FILE);
    }
}
