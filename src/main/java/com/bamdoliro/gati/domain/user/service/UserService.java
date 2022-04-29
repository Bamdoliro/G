package com.bamdoliro.gati.domain.user.service;

import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.domain.repository.UserRepository;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Status;
import com.bamdoliro.gati.domain.user.exception.PasswordMismatchException;
import com.bamdoliro.gati.domain.user.exception.UserAlreadyExistsException;
import com.bamdoliro.gati.domain.user.exception.UserNotFoundException;
import com.bamdoliro.gati.domain.user.presentation.dto.request.*;
import com.bamdoliro.gati.domain.user.presentation.dto.response.TokenResponseDto;
import com.bamdoliro.gati.domain.user.presentation.dto.response.getUserResponseDto;
import com.bamdoliro.gati.global.redis.RedisService;
import com.bamdoliro.gati.global.security.auth.AuthDetails;
import com.bamdoliro.gati.global.security.jwt.JwtProperties;
import com.bamdoliro.gati.global.security.jwt.JwtTokenProvider;
import com.bamdoliro.gati.global.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static com.bamdoliro.gati.global.security.jwt.JwtProperties.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieUtil cookieUtil;

    @Transactional
    public void createUser(CreateUserRequestDto dto) {
        validateCreateUserRequest(dto);
        userRepository.save(createUserFromCreateUserDto(dto));
    }

    private User createUserFromCreateUserDto(CreateUserRequestDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .birth(LocalDate.of(dto.getBirthYear(), dto.getBirthMonth(), dto.getBirthDay()))
                .gender(dto.getGender())
                .authority(Authority.ROLE_USER)
                .status(Status.NOT_VERIFIED)
                .build();
    }

    private void validateCreateUserRequest(CreateUserRequestDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> { throw UserAlreadyExistsException.EXCEPTION; });
    }

    public TokenResponseDto loginUser(LoginUserRequestDto dto, HttpServletResponse response) {
        validateLoginUserRequest(dto);

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

    private void validateLoginUserRequest(LoginUserRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }
    }

    public void logoutUser(HttpServletResponse response) {
        User user = getCurrentUser();

        Cookie tempCookie1 = cookieUtil.deleteCookie(ACCESS_TOKEN_NAME);
        Cookie tempCookie2 = cookieUtil.deleteCookie(REFRESH_TOKEN_NAME);
        response.addCookie(tempCookie1);
        response.addCookie(tempCookie2);

        redisService.deleteData(user.getEmail());
    }

    @Transactional(readOnly = true)
    public getUserResponseDto getUserInformation() {
        User user = getCurrentUser();

        return getUserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .birth(user.getBirth())
                .gender(user.getGender())
                .authority(user.getAuthority())
                .status(user.getStatus())
                .build();
    }

    @Transactional
    public void updateUserPassword(UpdateUserPasswordRequestDto dto) {
        User user = getCurrentUser();

        passwordCheck(user, dto.getCurrentPassword());
        user.updatePassword(passwordEncoder.encode(dto.getPassword()));
    }

    @Transactional
    public void updateUserName(UpdateUserNameRequestDto dto) {
        User user = getCurrentUser();

        user.updateName(dto.getName());
    }

    @Transactional
    public void deleteUser(DeleteUserRequestDto dto) {
        User user = getCurrentUser();

        passwordCheck(user, dto.getPassword());
        userRepository.delete(user);
    }

    private void passwordCheck(User user, String passwordToCheck) {
        if (!passwordEncoder.matches(passwordToCheck, user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }
    }

    public User getCurrentUser() {
        AuthDetails auth = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return auth.getUser();
    }

}
