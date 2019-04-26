package com.oli.Services;

import com.oli.Entities.OrderVideos;
import com.oli.Repository.OrderVideosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Author: Oliver
 */
@Slf4j
@Service
public class OrderVideosService extends ServiceAbstract<OrderVideos, Long> {
    private final OrderVideosRepository repository;

    public OrderVideosService(OrderVideosRepository repository) {
        this.repository = repository;
    }

    @Override
    PagingAndSortingRepository<OrderVideos, Long> getRepository() {
        return repository;
    }

    @Override
    public long countByCreatedBy(String user) {
        return repository.countByEmbeddedAudits_CreatedBy(user);
    }
}
