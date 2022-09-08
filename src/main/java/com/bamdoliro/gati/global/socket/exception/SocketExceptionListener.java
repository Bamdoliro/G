package com.bamdoliro.gati.global.socket.exception;

import java.util.List;

import com.bamdoliro.gati.global.error.ErrorResponse;
import com.bamdoliro.gati.global.error.exception.ErrorCode;
import com.bamdoliro.gati.global.error.exception.GatiException;
import com.bamdoliro.gati.global.socket.SocketEventProperty;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import io.netty.channel.ChannelHandlerContext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketExceptionListener implements ExceptionListener {

    @Override
    public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
        client.disconnect();
    }

    @Override
    public void onPingException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        return false;
    }

    private void runExceptionHandling(Exception e, SocketIOClient client) {
        final ErrorResponse message;

        if (e instanceof GatiException) {
            GatiException ex = (GatiException) e;
            message = new ErrorResponse(ex.getErrorCode());
        } else if (e.getCause() instanceof GatiException) {
            GatiException ex = (GatiException) e.getCause();
            message = new ErrorResponse(ex.getErrorCode());
        } else {
            e.printStackTrace();
            message = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        log.error("socket error! message: {}", message.getMessage());
        client.sendEvent(SocketEventProperty.ERROR_KEY, message);
    }

}