package com.oli.Repository;

import com.oli.Entities.DropDownListEntities;
import com.oli.Entities.Utils.EnumsUtils;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Oliver
 */
@Repository
public interface DropDownListEntityRepository extends PagingAndSortingRepository<DropDownListEntities, EnumsUtils.DropDownListEntities> {
    /**
     *
     * @param user
     * @return
     */
    long countByEmbeddedAudits_CreatedBy(String user);
}
