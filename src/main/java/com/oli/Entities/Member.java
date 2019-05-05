package com.oli.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.oli.Entities.Embedded.EmbeddedAudits;
import com.oli.Entities.Embedded.EmbeddedContact;
import com.oli.Entities.Embedded.EmbeddedPurchased;
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
 *
 * Member owns kids: NA
 */
@Slf4j
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members")
@EntityListeners(AuditingEntityListener.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = Member.class)
public class Member implements Emptiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
//    @Basic
//    private Long userId;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
    private User user;
    @Basic
    private String name;
    @Enumerated(EnumType.STRING)
    private EnumsUtils.Gender gender;
    @Basic
    private String school;
    @Basic
    private String major;
    @Basic
    private String landingOffer;
    @Basic
    private Integer yearOfGraduation;
    @Enumerated(EnumType.STRING)
    private EnumsUtils.Catalog jobTarget;
    @Basic
    private String personalInterest;
    @Basic
    private Boolean isVip = false;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedContact embeddedContact;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedPurchased embeddedPurchased;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedAudits embeddedAudits = new EmbeddedAudits();

    public Member(final EnumsUtils.Catalog jobTarget) {
        this.jobTarget = jobTarget;
    }

    public Member(User user, EnumsUtils.Catalog jobTarget) {
        this.user = user;
        this.jobTarget = jobTarget;
    }

    @Override
    @JsonIgnore
    public boolean isEmpty() {
        return id == null && (user == null || user.isEmpty()) ;
    }
}
