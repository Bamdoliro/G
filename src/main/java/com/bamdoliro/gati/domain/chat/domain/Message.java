package com.bamdoliro.gati.domain.chat.domain;

import com.bamdoliro.gati.domain.chat.domain.type.MessageType;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "chat_message_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Message extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private MessageType messageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_room")
    private Room room;

    @Builder
    public Message(String message, MessageType messageType, User user, Room room) {
        this.message = message;
        this.messageType = messageType;
        this.user = user;
        this.room = room;
    }
}
