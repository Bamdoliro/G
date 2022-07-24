package com.bamdoliro.gati.global.security.jwt;

import com.bamdoliro.gati.domain.user.exception.ExpiredAuthException;
import com.bamdoliro.gati.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtValidateService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    public String getEmail(String token) {
        return jwtTokenProvider.extractAllClaims(token)
                .get("email", String.class);
    }

    public void validateRefreshToken(String token) {
        if (redisService.getData(getEmail(token)) == null) {
            throw ExpiredAuthException.EXCEPTION;
        }
    }

}
