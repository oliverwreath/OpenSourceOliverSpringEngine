package com.oli.Entities;

import com.oli.Entities.Embedded.EmbeddedAudits;
import com.oli.Entities.Utils.EnumsUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Author: Oliver
 */
@Slf4j
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = DropDownListEntities.class)
public class DropDownListEntities {
    @Id
    @NaturalId
    @Enumerated(EnumType.STRING)
    private EnumsUtils.DropDownListEntities id;
    @Convert(converter = DropDownListConverter.class)
    private List<String> myValues;
    @Embedded
    @EqualsAndHashCode.Exclude
    private EmbeddedAudits embeddedAudits = new EmbeddedAudits();

    public DropDownListEntities(@NotBlank(message = "id can NOT be empty") EnumsUtils.DropDownListEntities id, List<String> myValues) {
        this.id = id;
        this.myValues = myValues;
    }
}
