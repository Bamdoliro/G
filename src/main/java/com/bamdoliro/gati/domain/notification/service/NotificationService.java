package com.bamdoliro.gati.domain.notification.service;

import com.bamdoliro.gati.domain.notification.domain.repository.NotificationRepository;
import com.bamdoliro.gati.domain.notification.presentation.dto.response.NotificationListResponse;
import com.bamdoliro.gati.domain.notification.presentation.dto.response.NotificationResponse;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserFacade userFacade;
    private final NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    public NotificationListResponse getNotification(Pageable pageable) {
        User user = userFacade.getCurrentUser();

        return new NotificationListResponse(
                notificationRepository.findAllByUser(user, pageable)
                .stream().map(NotificationResponse::of).collect(Collectors.toList())
        );
    }
}
