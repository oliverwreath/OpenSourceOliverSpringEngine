package com.oli.Services;

import com.oli.Entities.Admin;
import com.oli.Repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Author: Oliver
 */
@Slf4j
@Service
public class AdminService extends ServiceAbstract<Admin, Long> {
    private final AdminRepository repository;
    private final UserService userService;

    public AdminService(AdminRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    PagingAndSortingRepository<Admin, Long> getRepository() {
        return repository;
    }

    @Override
    public long countByCreatedBy(String user) {
        return repository.countByEmbeddedAudits_CreatedBy(user);
    }

    @Override
    public Admin saveOrReplace(Admin one) {
//        // must have User
//        if (one.getUser() == null || one.getUser().isEmpty()) {
//            log.debug("one = {}", one);
//            throw new ResponseStatusException(HttpStatus.PARTIAL_CONTENT, "user is not set!");
//        } else if (one.getUser().getUserType() == null) {
//            one.getUser().setUserType(EnumsUtils.UserType.ADMIN);
//            // don't forget to encode the password!
//            one.getUser().setPassword(SecurityConfig.passwordEncoder().encode(one.getUser().getPassword()));
//            one.getUser().setEnabled(true);
//        }
//        // User must have authorities
//        if (one.getUser().getAuthorities() == null || one.getUser().getAuthorities().isEmpty()) {
//            Set<Authority> set = new HashSet<>();
//            set.add(new Authority(EnumsUtils.Authorities.ROLE_ADMIN));
//            one.getUser().setAuthorities(set);
//        }
//        // User Must persisted first
//        if (one.getUser().getId() == null) {
//            one.setUser(userService.saveOrReplace(one.getUser()));
//        }
        return super.saveOrReplace(one);
    }
}
