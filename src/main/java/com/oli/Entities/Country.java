package com.oli.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Author: Oliver
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Country {
    @Id
    @NaturalId
    @NotBlank(message = "countryName cannot be empty")
    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;
}
