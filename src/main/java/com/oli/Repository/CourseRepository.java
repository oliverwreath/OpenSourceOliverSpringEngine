package com.oli.Repository;

import com.oli.Entities.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Oliver
 */
@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    /**
     *
     * @param user
     * @return
     */
    long countByEmbeddedAudits_CreatedBy(String user);
}
