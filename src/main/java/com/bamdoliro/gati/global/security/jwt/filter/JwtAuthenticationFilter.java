package com.bamdoliro.gati.global.security.jwt.filter;

import com.bamdoliro.gati.global.security.auth.AuthDetailsService;
import com.bamdoliro.gati.global.security.jwt.JwtValidateService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthDetailsService authDetailsService;
    private final JwtValidateService jwtValidateService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // TODO :: access token get
        final String accessToken = "";

        if (accessToken != null) {
            setAuthentication(accessToken, request);
        }
        filterChain.doFilter(request, response);
    }


    private void setAuthentication(String token, HttpServletRequest request) throws ExpiredJwtException {
        UserDetails userDetails = authDetailsService.loadUserByUsername(jwtValidateService.getEmail(token));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public JwtAuthenticationFilter(
            AuthDetailsService authDetailsService,
            JwtValidateService jwtValidateService
    ) {
        this.authDetailsService = authDetailsService;
        this.jwtValidateService = jwtValidateService;
    }
}
