package com.bamdoliro.gati.global.security.jwt;

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

    public boolean existsRefreshToken(String token) {
        String refreshToken = redisService.getData(getEmail(token));
        return refreshToken != null;
    }

}
