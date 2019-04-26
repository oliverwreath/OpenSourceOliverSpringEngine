package com.oli.RestController;

import com.google.common.base.Throwables;
import com.oli.Entities.Course;
import com.oli.Services.CourseService;
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
@RequestMapping("/api/courses")
public class CourseRestController implements RestControllerInterface<Course, Course, Long> {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Course create(@RequestBody @Valid Course newCourse) {
        return courseService.saveOrReplace(newCourse);
    }

    @GetMapping(value = {"/", "/all"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public List<Course> findAll() {
        return IterableUtils.toList(courseService.findAll());
    }

    // Single item
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Course findOne(@PathVariable(value = "id") Long id) {
        // Validate preconditions
        try {
            Validate.notNull(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Throwables.getRootCause(e).toString());
        }

        return courseService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course NOT FOUND with Id:" + id));
    }

    @PutMapping(value = "/{id2Put}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Course replace(@PathVariable("id2Put") Long id2Put, @RequestBody @Valid Course newCourse) {
        // Validate preconditions
        try {
            Validate.notNull(id2Put);
            Validate.notNull(newCourse);
            Validate.isTrue(!newCourse.isEmpty());
            Validate.isTrue(courseService.existsById(id2Put));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Throwables.getRootCause(e).toString());
        }

        log.debug("id2Put = {}, newCourse = {}", id2Put, newCourse);
        return courseService.saveOrReplace(newCourse);
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

//        if (!courseService.existsById(id2Delete)) {
//            throw (new ResponseStatusException(HttpStatus.NOT_FOUND, "course NOT FOUND with Id:" + id2Delete));
//        }

        courseService.deleteById(id2Delete);
    }
}
