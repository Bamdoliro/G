package com.bamdoliro.gati.domain.ddo.domain;

import com.bamdoliro.gati.domain.community.domain.Community;
import com.bamdoliro.gati.domain.ddo.domain.type.DdoStatus;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_ddo")
public class Ddo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ddo_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", length = 4000, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private DdoStatus status;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    @OneToMany(mappedBy = "ddo", cascade = CascadeType.ALL)
    private Set<Recommendation> recommendList = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ddo", cascade = CascadeType.ALL)
    private Set<DdoJoin> ddoJoinList = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @Builder
    public Ddo(String title, String content, DdoStatus status, int capacity, Community community, User writer, List<String> images, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.capacity = capacity;
        this.community = community;
        this.writer = writer;
        this.images = images;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setRelation(User user, Community community) {
        user.getDdoList().add(this);
        community.getDdoList().add(this);
        this.writer = user;
        this.community = community;
    }
}