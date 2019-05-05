package com.oli.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.oli.Entities.Embedded.EmbeddedAudits;
import com.oli.Entities.Utils.EnumsUtils;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities")
@EntityListeners(AuditingEntityListener.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = Authority.class)
public class Authority implements Emptiable {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String email;
    @Basic
    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private EnumsUtils.Authorities authority;
//    @Column(name = "user_id")
//    private Long userId;
//    @JsonBackReference(value = "user")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedAudits embeddedAudits = new EmbeddedAudits();

    @Override
    @JsonIgnore
    public boolean isEmpty() {
        return id == null && (authority == null);
    }

    public Authority(EnumsUtils.Authorities authority) {
        this.authority = authority;
    }
}
