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
//        scope = OrderCourses.class)
public class OrderCourses {
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
//    @Basic
//    private Long courseId;
    @JsonBackReference(value = "course")
    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Course course;
    @Basic
    private String courseName;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedStats embeddedStats;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedAudits embeddedAudits = new EmbeddedAudits();

    public OrderCourses(final String courseName) {
        this.courseName = courseName;
    }

    public OrderCourses(String courseName, Member member, Course course) {
        this.member = member;
        this.course = course;
        this.courseName = courseName;
    }
}
