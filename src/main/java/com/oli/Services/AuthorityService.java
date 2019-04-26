package com.oli.Services;

import com.oli.Entities.Authority;
import com.oli.Repository.AuthorityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Author: Oliver
 */
@Slf4j
@Service
public class AuthorityService extends ServiceAbstract<Authority, Long> {
    private final AuthorityRepository repository;

    public AuthorityService(AuthorityRepository repository) {
        this.repository = repository;
    }

    @Override
    PagingAndSortingRepository<Authority, Long> getRepository() {
        return repository;
    }

    @Override
    public long countByCreatedBy(String user) {
        return repository.countByEmbeddedAudits_CreatedBy(user);
    }
}
