package com.oli.Services;

import com.oli.Entities.User;
import com.oli.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author: Oliver
 */
@Slf4j
@Service
public class UserService extends ServiceAbstract<User, Long> {
    private final UserRepository repository;
    private final AuthorityService authorityService;

    public UserService(UserRepository repository, AuthorityService authorityService) {
        this.repository = repository;
        this.authorityService = authorityService;
    }

    @Override
    PagingAndSortingRepository<User, Long> getRepository() {
        return repository;
    }

    @Override
    public long countByCreatedBy(String user) {
        return repository.countByEmbeddedAudits_CreatedBy(user);
    }

    @Override
    public User saveOrReplace(User entity) {
//        if (entity.getAuthorities() != null && !entity.getAuthorities().isEmpty()) {
//            for (Authority authority : entity.getAuthorities()) {
////                authority.setUser(entity);
//                if (authority.getId() == null) {
//                    authority.setId(authorityService.saveOrReplace(authority).getId());
//                }
//            }
//        }
        return super.saveOrReplace(entity);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByUserEmailEquals(email);
    }
}
