package com.bamdoliro.gati.domain.board.domain;

import com.bamdoliro.gati.domain.board.domain.type.doPost.Status;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "do_post_table")
public class DoPost extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "do_post_id")
    private Long id;

    @Column(name = "title", length = 20, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", length = 4000, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "maxNumber", nullable = false)
    private int maxNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;

    @OneToMany(mappedBy = "doPost", cascade = CascadeType.ALL)
    private List<DoRecommendation> recommendList = new ArrayList<>();

    @OneToMany(mappedBy = "doPost", cascade = CascadeType.ALL)
    private List<DoJoin> joinList = new ArrayList<>();

    @Builder
    public DoPost(String title, String content, Status status, int maxNumber) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.maxNumber = maxNumber;
    }

    public void setRelation(User user) {
        user.getDoPostList().add(this);
        this.writer = user;
    }

}
