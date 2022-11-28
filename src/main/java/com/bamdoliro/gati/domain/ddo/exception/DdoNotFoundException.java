package com.bamdoliro.gati.domain.ddo.exception;

import com.bamdoliro.gati.domain.ddo.exception.error.DdoErrorProperty;
import com.bamdoliro.gati.global.error.exception.GatiException;

public class DdoNotFoundException extends GatiException {

    public static final DdoNotFoundException EXCEPTION = new DdoNotFoundException();

    private DdoNotFoundException() {
        super(DdoErrorProperty.DDO_NOT_FOUND);
    }
}
