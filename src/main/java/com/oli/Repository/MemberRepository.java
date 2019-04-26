package com.oli.Repository;

import com.oli.Entities.Member;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Oliver
 */
@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
    /**
     *
     * @param user
     * @return
     */
    long countByEmbeddedAudits_CreatedBy(String user);
}
