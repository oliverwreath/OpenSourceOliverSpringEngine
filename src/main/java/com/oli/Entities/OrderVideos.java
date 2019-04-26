package com.oli.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oli.Entities.Embedded.EmbeddedAudits;
import com.oli.Entities.Embedded.EmbeddedStats;
import com.oli.Entities.Utils.EnumsUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Author: Oliver
 */
@Slf4j
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = OrderVideos.class)
public class OrderVideos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
//    @Basic
//    private Long memberId;
    @JsonBackReference(value = "member")
    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Member member;
    @Enumerated(EnumType.STRING)
    private EnumsUtils.OrderType orderType;
    @Enumerated(EnumType.STRING)
    private EnumsUtils.Catalog catalog;
    @Basic
    private String courseName;
//    @Basic
//    private Long videoId;
    @JsonBackReference(value = "video")
    @ManyToOne
    @JoinColumn(name="video_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Video video;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedStats embeddedStats;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedAudits embeddedAudits = new EmbeddedAudits();

    public OrderVideos(final String courseName) {
        this.courseName = courseName;
    }

    public OrderVideos(String courseName, Member member, Video video) {
        this.member = member;
        this.courseName = courseName;
        this.video = video;
    }
}
