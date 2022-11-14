package com.bamdoliro.gati.global.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomCodeUtil {

    public static String make(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }
}
