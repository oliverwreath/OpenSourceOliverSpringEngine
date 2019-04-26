package com.oli.Repository;

import com.oli.Entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Oliver
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    /**
     *
     * @param user
     * @return
     */
    long countByEmbeddedAudits_CreatedBy(String user);
}
