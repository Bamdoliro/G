package com.bamdoliro.gati.domain.board.domain;

import com.bamdoliro.gati.domain.board.domain.type.report.ReportCategory;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportStatus;
import com.bamdoliro.gati.domain.board.domain.type.report.ReportType;
import com.bamdoliro.gati.domain.user.domain.User;
import com.bamdoliro.gati.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report_table")
public class Report extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportCategory reportCategory;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Builder
    public Report(ReportType type, ReportCategory category, String title, String content, User user, Board board) {
        this.reportType = type;
        this.reportCategory = category;
        this.title = title;
        this.content = content;
        this.reportStatus = ReportStatus.UNSOLVED;
        this.board = board;
        this.user = user;
    }

    public void setRelation() {
        board.getReportList().add(this);
        user.getReportList().add(this);
    }

}
