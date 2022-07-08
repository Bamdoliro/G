package com.bamdoliro.gati.domain.user.service;

import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.repository.UserRepository;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import com.bamdoliro.gati.domain.user.presentation.dto.request.*;
import com.bamdoliro.gati.domain.user.presentation.dto.response.GetUserResponseDto;
import com.bamdoliro.gati.domain.user.presentation.dto.response.TokenResponseDto;
import com.bamdoliro.gati.global.redis.RedisService;
import com.bamdoliro.gati.global.security.jwt.JwtTokenProvider;
import com.bamdoliro.gati.global.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.bamdoliro.gati.global.security.jwt.JwtProperties.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieUtil cookieUtil;
    private final UserFacade userFacade;

    @Transactional
    public void createUser(CreateUserRequestDto dto) {
        userFacade.checkUserByEmail(dto.getEmail());

        userRepository.save(dto.toEntity(passwordEncoder.encode(dto.getPassword())));
    }

    public TokenResponseDto loginUser(LoginUserRequestDto dto, HttpServletResponse response) {
        User user = userFacade.findUserByEmail(dto.getEmail());
        userFacade.checkUserPassword(user, dto.getPassword());

        final String accessToken = jwtTokenProvider.createAccessToken(dto.getEmail());
        final String refreshToken = jwtTokenProvider.createRefreshToken(dto.getEmail());
        redisService.setDataExpire(dto.getEmail(), refreshToken, REFRESH_TOKEN_VALID_TIME);

        Cookie accessTokenCookie = cookieUtil.createCookie(ACCESS_TOKEN_NAME, accessToken, ACCESS_TOKEN_VALID_TIME);
        Cookie refreshTokenCookie = cookieUtil.createCookie(REFRESH_TOKEN_NAME, refreshToken, REFRESH_TOKEN_VALID_TIME);
        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void logoutUser(HttpServletResponse response) {
        User user = userFacade.getCurrentUser();

        Cookie tempCookie1 = cookieUtil.deleteCookie(ACCESS_TOKEN_NAME);
        Cookie tempCookie2 = cookieUtil.deleteCookie(REFRESH_TOKEN_NAME);
        response.addCookie(tempCookie1);
        response.addCookie(tempCookie2);

        redisService.deleteData(user.getEmail());
    }

    @Transactional(readOnly = true)
    public GetUserResponseDto getUserInformation() {
        User user = userFacade.getCurrentUser();

        return GetUserResponseDto.of(user);
    }

    @Transactional
    public void updateUserPassword(UpdateUserPasswordRequestDto dto) {
        User user = userFacade.getCurrentUser();

        userFacade.checkUserPassword(user, dto.getCurrentPassword());
        user.updatePassword(passwordEncoder.encode(dto.getPassword()));
    }

    @Transactional
    public void updateUserName(UpdateUserNameRequestDto dto) {
        User user = userFacade.getCurrentUser();

        user.updateName(dto.getName());
    }

    @Transactional
    public void deleteUser(DeleteUserRequestDto dto) {
        User user = userFacade.getCurrentUser();

        userFacade.checkUserPassword(user, dto.getPassword());
        userRepository.delete(user);
    }
}
