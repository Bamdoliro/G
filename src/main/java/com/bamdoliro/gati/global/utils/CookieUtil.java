package com.bamdoliro.gati.global.utils;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieUtil {

    public Cookie createCookie(String cookieName, String value, long time){
        Cookie token = new Cookie(cookieName,value);
        token.setHttpOnly(true);
        token.setMaxAge((int) time);
        token.setPath("/");
        return token;
    }

    public Cookie getCookie(HttpServletRequest request, String cookieName){
        final Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }

    public Cookie deleteCookie(String cookieName) {
        Cookie cookieToDelete = new Cookie(cookieName, null);
        cookieToDelete.setMaxAge(0);
        cookieToDelete.setPath("/");
        return cookieToDelete;
    }
}