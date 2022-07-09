package com.bamdoliro.gati.domain.board.domain;

import com.bamdoliro.gati.domain.board.domain.type.doPost.Status;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DoPost extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "do_post_id", nullable = false)
    private Long id;
    private String title;

    @Column(name = "content", length = 4000, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int numberOfPeople;

    @OneToMany(mappedBy = "doPost", cascade = CascadeType.ALL)
    private List<DoRecommendation> recommendList = new ArrayList<>();

    @Builder
    public DoPost(String title, String content, Status status, int numberOfPeople) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.numberOfPeople = numberOfPeople;
    }
}
