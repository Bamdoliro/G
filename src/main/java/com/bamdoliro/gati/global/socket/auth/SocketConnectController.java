package com.bamdoliro.gati.global.socket.auth;

import com.bamdoliro.gati.global.security.jwt.JwtValidateService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocketConnectController {

    private final JwtValidateService jwtValidateService;

    @OnConnect
    public void onConnect(SocketIOClient client) {
        String token = client.getHandshakeData().getSingleUrlParam("authorization");
        client.set(SocketAuthenticationProperty.USER_KEY, jwtValidateService.getEmail(token));
    }
}