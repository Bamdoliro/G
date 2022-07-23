package com.bamdoliro.gati.domain.ddo.domain;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.ddo.domain.type.Status;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ddo_table")
public class Ddo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ddo_id")
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

    @OneToMany(mappedBy = "ddo", cascade = CascadeType.ALL)
    private List<Recommendation> recommendList = new ArrayList<>();

    @OneToMany(mappedBy = "ddo", cascade = CascadeType.ALL)
    private List<DdoJoin> ddoJoinList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @Builder
    public Ddo(String title, String content, Status status, int maxNumber, Community community, User writer) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.maxNumber = maxNumber;
        this.community = community;
        this.writer = writer;
    }

    public void setRelation(User user, Community community) {
        user.getDdoList().add(this);
        community.getDdoList().add(this);
        this.writer = user;
        this.community = community;
    }

}
