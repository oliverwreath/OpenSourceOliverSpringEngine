package com.oli.RestController;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;

@Rollback
public abstract class RestControllerTestAbstract<T, ID> {
    protected abstract T getTestEntity();

    @Test
    @WithMockUser(roles = "ADMIN")
    abstract void givenCRUD_whenAdded_thenFound_whenUpdate_thenMatch_whenDelete_thenGone() throws Exception;
}
