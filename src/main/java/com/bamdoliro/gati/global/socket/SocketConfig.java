package com.bamdoliro.gati.global.socket;

import com.bamdoliro.gati.global.socket.exception.SocketExceptionListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocketConfig {

    @Value("${socket.config.port}")
    private int port;

    private final SocketExceptionListener socketExceptionListener;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setPort(port);
        config.setOrigin("*");
        config.setExceptionListener(socketExceptionListener);
        com.corundumstudio.socketio.SocketConfig socketConfig = config.getSocketConfig();
        socketConfig.setReuseAddress(true);
        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }
}
