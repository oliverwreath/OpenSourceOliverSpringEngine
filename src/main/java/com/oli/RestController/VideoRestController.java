package com.oli.RestController;

import com.google.common.base.Throwables;
import com.oli.Entities.Video;
import com.oli.Services.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: Oliver
 */
@Slf4j
@RestController
@RequestMapping("/api/videos")
public class VideoRestController implements RestControllerInterface<Video, Video, Long> {
    @Autowired
    private VideoService videoService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Video create(@RequestBody @Valid Video newVideo) {
        return videoService.saveOrReplace(newVideo);
    }

    @GetMapping(value = {"/", "/all"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public List<Video> findAll() {
        return IterableUtils.toList(videoService.findAll());
    }

    // Single item
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Video findOne(@PathVariable(value = "id") Long id) {
        // Validate preconditions
        try {
            Validate.notNull(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Throwables.getRootCause(e).toString());
        }

        return videoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "video NOT FOUND with Id:" + id));
    }

    @PutMapping(value = "/{id2Put}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Video replace(@PathVariable("id2Put") Long id2Put, @RequestBody @Valid Video newVideo) {
        // Validate preconditions
        try {
            Validate.notNull(id2Put);
            Validate.notNull(newVideo);
            Validate.isTrue(!newVideo.isEmpty());
            Validate.isTrue(videoService.existsById(id2Put));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Throwables.getRootCause(e).toString());
        }

        log.debug("id2Put = {}, newVideo = {}", id2Put, newVideo);
        return videoService.saveOrReplace(newVideo);
    }

    @DeleteMapping(value = "/{id2Delete}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id2Delete") Long id2Delete) {
        // Validate preconditions
        try {
            Validate.notNull(id2Delete);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Throwables.getRootCause(e).toString());
        }

//        if (!videoService.existsById(id2Delete)) {
//            throw (new ResponseStatusException(HttpStatus.NOT_FOUND, "video NOT FOUND with Id:" + id2Delete));
//        }

        videoService.deleteById(id2Delete);
    }
}
