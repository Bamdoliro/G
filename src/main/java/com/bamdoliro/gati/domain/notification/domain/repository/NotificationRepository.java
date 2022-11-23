package com.bamdoliro.gati.domain.notification.domain.repository;

import com.bamdoliro.gati.domain.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
