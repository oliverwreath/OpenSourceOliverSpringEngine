package com.oli.IntegrationTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        properties = "spring.datasource.type=com.zaxxer.hikari.HikariDataSource"
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HikariIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void hikariConnectionPoolIsConfigured() {
        assertThat("com.zaxxer.hikari.HikariDataSource").isEqualToIgnoringCase(dataSource.getClass().getName());
    }
}

