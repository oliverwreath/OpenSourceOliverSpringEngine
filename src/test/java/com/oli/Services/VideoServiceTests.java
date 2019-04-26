package com.oli.Services;

import com.oli.OliverSpringEngineApplication;
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
class VideoServiceTests extends ServiceTestAbstract<Video, Long> {
    @Autowired
    private VideoService service;

    @Override
    protected Video getTestEntity() {
        return new Video(TEST_STRING_1);
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
        Video persistedEntity = service.saveOrReplace(getTestEntity());
        Long id = persistedEntity.getId();
        // exists
        assertThat(service.existsById(id)).isTrue();
        // R read
        Optional<Video> foundEntityOptional = service.findById(persistedEntity.getId());
        assertThat(foundEntityOptional).isNotNull();
        assertThat(foundEntityOptional.isPresent()).isTrue();
        // same
        Video foundEntity = foundEntityOptional.get();
        assertThat(foundEntity).isEqualTo(persistedEntity);
        // U update
        foundEntity.setVideoName(TEST_STRING_5);
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
