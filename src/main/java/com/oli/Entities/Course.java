package com.oli.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.List;

/**
 * Author: Oliver
 */
@Slf4j
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
@EntityListeners(AuditingEntityListener.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = Course.class)
public class Course implements Emptiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Basic
    private String courseName;
    @Enumerated(EnumType.STRING)
    private EnumsUtils.Catalog catalog;
    @Basic
    @Column(precision = 13, scale = 5)
    private BigDecimal coursePrice;
    @JsonManagedReference("Course->Video")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, mappedBy = "course")
    @EqualsAndHashCode.Exclude
    private List<Video> videos;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedStats embeddedStats;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedAudits embeddedAudits = new EmbeddedAudits();

    public Course(final String courseName) {
        this.courseName = courseName;
    }

    @Override
    @JsonIgnore
    public boolean isEmpty() {
        return id == null && (catalog == null || StringUtils.isBlank(courseName));
    }
}
