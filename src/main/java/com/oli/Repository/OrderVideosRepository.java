package com.oli.Repository;

import com.oli.Entities.OrderVideos;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Oliver
 */
@Repository
public interface OrderVideosRepository extends PagingAndSortingRepository<OrderVideos, Long> {
    /**
     *
     * @param user
     * @return
     */
    long countByEmbeddedAudits_CreatedBy(String user);
}
