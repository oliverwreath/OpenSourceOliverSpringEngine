package com.oli.Entities.Embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;

/**
 * Author: Oliver
 */
@Slf4j
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedPurchased {
    private Long video_id;
}
