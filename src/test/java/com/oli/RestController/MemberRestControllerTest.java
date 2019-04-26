package com.oli.RestController;

import com.oli.Entities.Member;
import com.oli.Entities.Utils.EnumsUtils;
import com.oli.Services.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: Oliver
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberRestControllerTest extends RestControllerTestAbstract<Member, Long> {
    @Autowired
    private WebApplicationContext ctx;
    @Autowired
    private MemberService service;

    private static final String API_URL = "/api/members/";
    private static MockMvc mockMvc;
    private static RestController restController;

    void setup() {
        if (mockMvc == null) {
            DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.ctx);
            mockMvc = builder.build();
        }
        if (restController == null) {
            restController = new RestController(API_URL, mockMvc);
        }
    }

    @Override
    protected Member getTestEntity() {
//        Member testEntity = new Member(new User("email_MemberRestControllerTest@example.com", "pass_example"), EnumsUtils.Catalog.四大_BIG4);
        Member testEntity = new Member(EnumsUtils.Catalog.四大_BIG4);
        log.debug("testEntity = {}", testEntity);
        return testEntity;
    }

    @BeforeAll
    void initialize() {
        log.info("initialize >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // Zero tolerance to test leftovers
        setup();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void givenCRUD_whenAdded_thenFound_whenUpdate_thenMatch_whenDelete_thenGone() throws Exception {
        // C write
        Member persistedEntity = restController.save(getTestEntity());
        Long id = persistedEntity.getId();
        log.debug("id = {}", id);
        // exists
        assertThat(service.existsById(id)).isTrue();
        // R read
        Member foundEntity = restController.findById(id);
        assertThat(foundEntity).isNotNull();
        // same
        assertThat(foundEntity).isEqualTo(persistedEntity);
        // U update
        foundEntity.setJobTarget(EnumsUtils.Catalog.快消_CPG);
        persistedEntity = restController.replace(foundEntity, foundEntity.getId());
        // still same
        foundEntity = restController.findById(id);
        assertThat(foundEntity).isEqualTo(persistedEntity);
        // D delete
        restController.deleteById(id);
        // not found
        assertThat(service.existsById(id)).isFalse();
    }

    private static class RestController extends RestControllerTestHelperAbstract<Member, Long> {
        RestController(String API_URL, MockMvc mockMvc) {
            super(API_URL, mockMvc, Member.class);
        }
    }

    @AfterAll
    void cleanUp() {
        log.info("cleanUp >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // Zero tolerance to test leftovers
    }
}
