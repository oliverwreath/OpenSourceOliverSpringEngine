package com.oli.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oli.Entities.Embedded.EmbeddedAudits;
import com.oli.Entities.Embedded.EmbeddedStats;
import com.oli.Entities.Utils.EnumsUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Author: Oliver
 */
@Slf4j
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "videos")
@EntityListeners(AuditingEntityListener.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = Video.class)
public class Video implements Emptiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Basic
    private String videoName;
    @Enumerated(EnumType.STRING)
    private EnumsUtils.Catalog catalog;
//    @Basic
//    private String course;
    @ManyToOne()
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Exclude
    private Course course;
    @Basic
    private String courseName;
    @Basic
    @Column(precision = 13, scale = 5)
    private BigDecimal videoPrice;
    @Basic
    private String videoLink;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedStats embeddedStats;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedAudits embeddedAudits = new EmbeddedAudits();

    public Video(final String videoName) {
        this.videoName = videoName;
    }

    @Override
    @JsonIgnore
    public boolean isEmpty() {
        return id == null && (StringUtils.isBlank(videoName));
    }
}
