package com.bamdoliro.gati.domain.notification.domain.repository;

import com.bamdoliro.gati.domain.notification.domain.Notification;
import com.bamdoliro.gati.domain.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUser(User user, Pageable pageable);
}
