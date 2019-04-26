package com.oli.Repository;

import com.oli.Entities.OrderCourses;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Oliver
 */
@Repository
public interface OrderCoursesRepository extends PagingAndSortingRepository<OrderCourses, Long> {
    /**
     *
     * @param user
     * @return
     */
    long countByEmbeddedAudits_CreatedBy(String user);
}
