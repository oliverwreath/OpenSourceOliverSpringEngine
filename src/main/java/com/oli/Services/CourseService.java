package com.oli.Services;

import com.oli.Entities.Course;
import com.oli.Repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Author: Oliver
 */
@Slf4j
@Service
public class CourseService extends ServiceAbstract<Course, Long> {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    PagingAndSortingRepository<Course, Long> getRepository() {
        return repository;
    }

    @Override
    public long countByCreatedBy(String user) {
        return repository.countByEmbeddedAudits_CreatedBy(user);
    }
}
