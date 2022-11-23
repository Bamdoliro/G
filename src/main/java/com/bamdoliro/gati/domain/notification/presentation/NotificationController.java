package com.bamdoliro.gati.domain.notification.presentation;

import com.bamdoliro.gati.domain.notification.presentation.dto.response.NotificationListResponse;
import com.bamdoliro.gati.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public NotificationListResponse getNotification(Pageable pageable) {
        return notificationService.getNotification(pageable);

    }
}
