package com.bamdoliro.gati.domain.notification.domain;

import com.bamdoliro.gati.domain.notification.domain.type.NotificationType;
import com.bamdoliro.gati.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tbl_notification")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column(length = 200, nullable = false)
    private String message;

    @Column(length = 20, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private NotificationType type;

    @Column(nullable = false)
    private Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Notification(String message, NotificationType type, User user) {
        this.message = message;
        this.type = type;
        this.isRead = false;
        this.user = user;
    }

    public void read() {
        this.isRead = true;
    }
}
