package com.oli.Services;

import com.oli.Entities.DropDownListEntities;
import com.oli.Entities.Utils.EnumsUtils;
import com.oli.Repository.DropDownListEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Author: Oliver
 */
@Slf4j
@Service
public class DropDownListEntityService extends ServiceAbstract<DropDownListEntities, EnumsUtils.DropDownListEntities> {
    private final DropDownListEntityRepository repository;

    public DropDownListEntityService(DropDownListEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    PagingAndSortingRepository<DropDownListEntities, EnumsUtils.DropDownListEntities> getRepository() {
        return repository;
    }

    @Override
    public long countByCreatedBy(String user) {
        return repository.countByEmbeddedAudits_CreatedBy(user);
    }
}
