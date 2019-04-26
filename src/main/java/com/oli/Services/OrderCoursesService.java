package com.oli.Services;

import com.oli.Entities.OrderCourses;
import com.oli.Repository.OrderCoursesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Author: Oliver
 */
@Slf4j
@Service
public class OrderCoursesService extends ServiceAbstract<OrderCourses, Long> {
    private final OrderCoursesRepository repository;

    public OrderCoursesService(OrderCoursesRepository repository) {
        this.repository = repository;
    }

    @Override
    PagingAndSortingRepository<OrderCourses, Long> getRepository() {
        return repository;
    }

    @Override
    public long countByCreatedBy(String user) {
        return repository.countByEmbeddedAudits_CreatedBy(user);
    }
}
