package com.oli.Services;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;

/**
 * Author: Oliver
 */
@Rollback
public interface ServiceTestInterface<T, ID> {
    @Test
    @WithMockUser(roles = "ADMIN")
    void givenCRUD_whenAdded_thenFound_whenUpdate_thenMatch_whenDelete_thenGone();
}
