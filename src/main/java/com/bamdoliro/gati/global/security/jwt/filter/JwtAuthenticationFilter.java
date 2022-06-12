package com.bamdoliro.gati.global.security.jwt.filter;

import com.bamdoliro.gati.global.security.auth.AuthDetailsService;
import com.bamdoliro.gati.global.security.jwt.JwtTokenProvider;
import com.bamdoliro.gati.global.security.jwt.JwtValidateService;
import com.bamdoliro.gati.global.utils.CookieUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bamdoliro.gati.global.security.jwt.JwtProperties.*;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthDetailsService authDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtValidateService jwtValidateService;
    private final CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final Cookie accessTokenCookie = cookieUtil.getCookie(request, ACCESS_TOKEN_NAME);

        if (accessTokenCookie != null) {
            try {
                String accessToken = accessTokenCookie.getValue();
                setAuthentication(accessToken, request);
            } catch (ExpiredJwtException e) {
                final Cookie refreshTokenCookie = cookieUtil.getCookie(request, REFRESH_TOKEN_NAME);
                if (refreshTokenCookie != null) {
                    try {
                        String refreshToken = refreshTokenCookie.getValue();
                        if (jwtValidateService.existsRefreshToken(refreshToken)) {
                            setAuthentication(refreshToken, request);
                            setNewAccessToken(refreshToken, response);
                        }
                    } catch (ExpiredJwtException re) {
                        // TODO re login
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setNewAccessToken(String refreshToken, HttpServletResponse response) {
        String newAccessToken = jwtTokenProvider.createAccessToken(
                jwtValidateService.getEmail(refreshToken));
        Cookie newAccessTokenCookie = cookieUtil.createCookie(ACCESS_TOKEN_NAME, newAccessToken, ACCESS_TOKEN_VALID_TIME);
        response.addCookie(newAccessTokenCookie);
    }

    private void setAuthentication(String token, HttpServletRequest request) throws ExpiredJwtException {
        UserDetails userDetails = authDetailsService.loadUserByUsername(jwtValidateService.getEmail(token));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public JwtAuthenticationFilter(
            AuthDetailsService authDetailsService,
            JwtTokenProvider jwtTokenProvider,
            JwtValidateService jwtValidateService,
            CookieUtil cookieUtil
    ) {
        this.authDetailsService = authDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtValidateService = jwtValidateService;
        this.cookieUtil = cookieUtil;
    }
}
