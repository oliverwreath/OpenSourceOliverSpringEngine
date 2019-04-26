package com.oli.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Author: Oliver
 */
public interface Emptiable {
    /**
     * This is for business empty. e.g. if an authority has no role is means nothing. That said it is empty regardless of other data.
     * @return
     */
    @JsonIgnore
    boolean isEmpty();
}
