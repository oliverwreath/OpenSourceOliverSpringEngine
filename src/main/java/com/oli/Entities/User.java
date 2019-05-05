package com.oli.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oli.Entities.Embedded.EmbeddedAudits;
import com.oli.Entities.Utils.EnumsUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: Oliver
 *
 * User owns kids: Admin, Member and Authorities
 */
@Slf4j
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = User.class)
public class User implements Emptiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Basic
    private String userEmail;
    @Basic
    private String password;
    @Basic
    @Column(name = "enabled")
    private Boolean enabled;
    @Enumerated(EnumType.STRING)
    private EnumsUtils.UserType userType;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="user_id")
    @EqualsAndHashCode.Exclude
    private Set<Authority> authorities;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedAudits embeddedAudits = new EmbeddedAudits();

//    @JsonBackReference(value = "admin")
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Admin admin;

//    @JsonBackReference(value = "member")
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Member member;

    public User(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    @JsonIgnore
    public boolean isEmpty() {
        return id == null && (StringUtils.isBlank(userEmail) || StringUtils.isBlank(password));
    }

    public User(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
}
