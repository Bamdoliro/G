package com.bamdoliro.gati.domain.community.domain;

import com.bamdoliro.gati.domain.board.domain.Board;
import com.bamdoliro.gati.domain.ddo.domain.Ddo;
import com.bamdoliro.gati.domain.community.domain.type.CommunityStatus;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import com.bamdoliro.gati.global.utils.BooleanToYNConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_community")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Community extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 1000, nullable = false)
    private String introduction;

    @Column(nullable = false)
    private int capacity;

    @Column(length = 6, nullable = false)
    private String code;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(length = 1, nullable = false)
    private Boolean isPublic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    private List<Board> boards = new ArrayList<>();

    @Column(length = 4, nullable = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private CommunityStatus communityStatus;

    @OneToMany(mappedBy = "community")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<Ddo> ddoList = new ArrayList<>();


    @Builder
    public Community(String name, String introduction, int capacity, String code, Boolean isPublic, String password) {
        this.name = name;
        this.introduction = introduction;
        this.capacity = capacity;
        this.code = code;
        this.isPublic = isPublic;
        this.password = password;
        this.communityStatus = CommunityStatus.EXISTED;
    }

    public void updateCommunity(String name, String introduction, Boolean isPublic, String password) {
        this.name = name;
        this.introduction = introduction;
        this.isPublic = isPublic;
        this.password = password;
    }

    public void deleteCommunity() {
        this.communityStatus = CommunityStatus.DELETED;
    }
}
