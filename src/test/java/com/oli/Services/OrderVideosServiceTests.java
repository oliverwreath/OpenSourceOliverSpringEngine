package com.oli.Services;

import com.oli.OliverSpringEngineApplication;
import com.oli.Entities.Member;
import com.oli.Entities.OrderVideos;
import com.oli.Entities.Video;
import com.oli.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.oli.TestUtil.TEST_STRING_1;
import static com.oli.TestUtil.TEST_STRING_5;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: Oliver
 */
@Slf4j
@Rollback
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OliverSpringEngineApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderVideosServiceTests extends ServiceTestAbstract<OrderVideos, Long> {
    @Autowired
    private OrderVideosService service;
    @Autowired
    private MemberService memberService;
    @Autowired
    private VideoService videoService;

    @Override
    protected OrderVideos getTestEntity() {
        Page<Member> all = memberService.findAll(PageRequest.of(0, 1));
        Member member1 = all.getContent().get(0);
        Page<Video> all1 = videoService.findAll(PageRequest.of(0, 1));
        Video video1 = all1.getContent().get(0);
        OrderVideos testEntity = new OrderVideos(TEST_STRING_1, member1, video1);
        log.debug("testEntity = {}", testEntity);
        return testEntity;
    }

    @BeforeAll
    void initialize() {
        log.info("initialize >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // Zero tolerance to test leftovers
        assertThat(service.countByCreatedBy(TestUtil.USER)).isEqualTo(0L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void givenCRUD_whenAdded_thenFound_whenUpdate_thenMatch_whenDelete_thenGone() {
        // C write
        OrderVideos persistedEntity = service.saveOrReplace(getTestEntity());
        Long id = persistedEntity.getId();
        // exists
        assertThat(service.existsById(id)).isTrue();
        // R read
        Optional<OrderVideos> foundEntityOptional = service.findById(persistedEntity.getId());
        assertThat(foundEntityOptional).isNotNull();
        assertThat(foundEntityOptional.isPresent()).isTrue();
        // same
        OrderVideos foundEntity = foundEntityOptional.get();
        assertThat(foundEntity).isEqualTo(persistedEntity);
        // U update
        foundEntity.setCourseName(TEST_STRING_5);
        persistedEntity = service.saveOrReplace(foundEntity);
        // still same
        foundEntityOptional = service.findById(id);
        foundEntity = foundEntityOptional.get();
        assertThat(foundEntity).isEqualTo(persistedEntity);
        // D deleteById
        service.deleteById(persistedEntity.getId());
        // not found
        assertThat(service.existsById(id)).isFalse();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void givenCount_whenFindAll_thenMatch() {
        long count = service.count();
        assertThat(count).isEqualTo(IterableUtils.size(service.findAll()));
    }

    @AfterAll
    void cleanUp() {
        log.info("cleanUp >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // Zero tolerance to test leftovers
        assertThat(service.countByCreatedBy(TestUtil.USER)).isEqualTo(0L);
    }
}
