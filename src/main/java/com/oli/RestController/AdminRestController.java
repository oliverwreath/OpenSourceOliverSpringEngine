package com.oli.RestController;

import com.google.common.base.Throwables;
import com.oli.Entities.Admin;
import com.oli.Services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: Oliver
 */
@Slf4j
@RestController
@RequestMapping("/api/admins")
public class AdminRestController implements RestControllerInterface<Admin, Admin, Long> {
    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Admin create(@RequestBody @Valid Admin one) {

        log.debug("one = {}", one);
        if (one.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post should NOT have ID!");
        }
        return adminService.saveOrReplace(one);
    }

    @GetMapping(value = {"/", "/all"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public List<Admin> findAll() {
        return IterableUtils.toList(adminService.findAll());
    }

    // Single item
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Admin findOne(@PathVariable(value = "id") Long id) {
        // Validate preconditions
        try {
            Validate.notNull(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Throwables.getRootCause(e).toString());
        }

        return adminService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "admin NOT FOUND with Id:" + id));
    }

    @PutMapping(value = "/{id2Put}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Admin replace(@PathVariable("id2Put") Long id2Put, @RequestBody @Valid Admin newAdmin) {
        // Validate preconditions
        try {
            Validate.notNull(id2Put);
            Validate.notNull(newAdmin);
            Validate.isTrue(!newAdmin.isEmpty());
            Validate.isTrue(adminService.existsById(id2Put));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Throwables.getRootCause(e).toString());
        }

        log.debug("id2Put = {}, newAdmin = {}", id2Put, newAdmin);
        return adminService.saveOrReplace(newAdmin);
    }

    @DeleteMapping(value = "/{id2Delete}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id2Delete") Long id2Delete) {
        // Validate preconditions
        try {
            Validate.notNull(id2Delete);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Throwables.getRootCause(e).toString());
        }

        adminService.deleteById(id2Delete);
    }
}
