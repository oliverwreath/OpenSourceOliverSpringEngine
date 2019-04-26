package com.oli.Entities;

import com.oli.Entities.Utils.EnumsUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Author: Oliver
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Catalog {
    @Id
    @NaturalId
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "catalogName cannot be empty")
    @Column(name = "catalog_name", nullable = false, unique = true)
    private EnumsUtils.Catalog catalogName;
}
