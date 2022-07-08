package com.bamdoliro.gati.domain.board.domain;

import com.bamdoliro.gati.domain.board.domain.type.doPost.Status;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;

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
    private String title;

    @Size(min = 10, max = 2000)
    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;
    private int numberOfPeople;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "doPost", cascade = CascadeType.ALL)
    private List<DoRecommend> recommendList = new ArrayList<>();

    @Builder
    public DoPost(String title, String content, Status status, int numberOfPeople) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.numberOfPeople = numberOfPeople;
    }

    public void setRelation(User user) {
        user.getDoPostList().add(this);
        this.user = user;
    }
}
