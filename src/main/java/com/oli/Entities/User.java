package com.oli.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oli.Entities.Embedded.EmbeddedAudits;
import com.oli.Entities.Utils.EnumsUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Author: Oliver
 * <p>
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
    private Boolean enabled = false;
    @Enumerated(EnumType.STRING)
    private EnumsUtils.UserType userType;
    @Basic
    private UUID token;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    @EqualsAndHashCode.Exclude
    private Set<Authority> authorities = new HashSet<>(Collections.singletonList(new Authority(EnumsUtils.Authorities.ROLE_USER)));
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

    /**
     * Constructors
     *
     * @param userEmail
     */
    public User(String userEmail) {
        this.userEmail = userEmail;
        for (Authority authority : this.authorities) {
            authority.setUser(this);
        }
    }

    public User(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
        for (Authority authority : this.authorities) {
            authority.setUser(this);
        }
    }

    public User(String userEmail, String password, UUID token) {
        this.userEmail = userEmail;
        this.password = password;
        this.token = token;
        for (Authority authority : this.authorities) {
            authority.setUser(this);
        }
    }

    @Override
    @JsonIgnore
    public boolean isEmpty() {
        return id == null && (StringUtils.isBlank(userEmail) || StringUtils.isBlank(password));
    }
}
