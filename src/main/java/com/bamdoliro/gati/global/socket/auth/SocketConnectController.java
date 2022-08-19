package com.bamdoliro.gati.global.socket.auth;

import com.bamdoliro.gati.global.security.jwt.JwtProperties;
import com.bamdoliro.gati.global.security.jwt.JwtValidateService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketConnectController {

    private final JwtValidateService jwtValidateService;

    @OnConnect
    public void onConnect(SocketIOClient client) {
        String token = client.getHandshakeData().getHttpHeaders().get(JwtProperties.JWT_HEADER);
        client.set(SocketAuthenticationProperty.USER_KEY, jwtValidateService.getEmail(token));
    }
}