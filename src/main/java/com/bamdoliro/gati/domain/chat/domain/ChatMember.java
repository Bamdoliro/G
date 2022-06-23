package com.bamdoliro.gati.domain.chat.domain;

import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "chat_member_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_member_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_member_room")
    private Room room;

    @Builder
    public ChatMember(User user, Room room) {
        this.user = user;
        this.room = room;
    }
}
