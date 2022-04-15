package com.bamdoliro.gati.global.security.jwt;

public class JwtProperties {
    public static final long ACCESS_TOKEN_VALID_TIME = 30 * 60 * 1000L;
    public static final long REFRESH_TOKEN_VALID_TIME = 30 * 24 * 60 * 60 * 1000L;
    public static final String ACCESS_TOKEN_NAME = "accessToken";
    public static final String REFRESH_TOKEN_NAME = "refreshTOken";
}
