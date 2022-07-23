package com.bamdoliro.gati.domain.user.domain;

import com.bamdoliro.gati.domain.board.domain.*;
import com.bamdoliro.gati.domain.ddo.facade.domain.DdoJoin;
import com.bamdoliro.gati.domain.ddo.facade.domain.Ddo;
import com.bamdoliro.gati.domain.ddo.facade.domain.Recommendation;
import com.bamdoliro.gati.domain.user.domain.type.Authority;
import com.bamdoliro.gati.domain.user.domain.type.Gender;
import com.bamdoliro.gati.domain.user.domain.type.Status;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(length = 7, nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", length = 25, nullable = false)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private Status status;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "liker", cascade = CascadeType.ALL)
    private List<BoardLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Report> reportList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recommendation> recommendList = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Ddo> ddoList = new ArrayList<>();

    @OneToMany(mappedBy = "joiner", cascade = CascadeType.ALL)
    private List<DdoJoin> ddoJoinList = new ArrayList<>();

    @Builder
    public User(String email, String password, String name, LocalDate birth, Gender gender, Authority authority, Status status) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.authority = authority;
        this.status = status;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
