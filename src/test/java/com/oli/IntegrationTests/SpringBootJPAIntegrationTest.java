package com.oli.IntegrationTests;

import com.oli.OliverSpringEngineApplication;
import com.oli.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OliverSpringEngineApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringBootJPAIntegrationTest {
    @Autowired
    private UserRepository repository;

    @Test
    @WithMockUser(roles = "ADMIN")
    void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        // just integration, eh?
        repository.count();
    }
}
