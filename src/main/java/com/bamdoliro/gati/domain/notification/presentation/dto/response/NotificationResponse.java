package com.bamdoliro.gati.domain.notification.presentation.dto.response;

import com.bamdoliro.gati.domain.notification.domain.Notification;
import com.bamdoliro.gati.domain.notification.domain.type.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationResponse {
    private Long id;
    private String message;
    private NotificationType type;
    private Boolean isRead;

    public static NotificationResponse of(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .type(notification.getType())
                .isRead(notification.getIsRead())
                .build();
    }
}
