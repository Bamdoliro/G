package com.bamdoliro.gati.global.security.jwt;

public class JwtProperties {
    public static final long ACCESS_TOKEN_VALID_TIME = 2 * 60 * 1000L;
    public static final long REFRESH_TOKEN_VALID_TIME = 5 * 60 * 1000L;
    public static final String JWT_PREFIX = "Bearer";
    public static final String JWT_HEADER = "Authorization";
}
