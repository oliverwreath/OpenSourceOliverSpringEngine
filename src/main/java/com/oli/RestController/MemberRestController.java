package com.oli.RestController;

import com.google.common.base.Throwables;
import com.oli.Entities.Member;
import com.oli.Services.MemberService;
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
@RequestMapping("/api/members")
public class MemberRestController implements RestControllerInterface<Member, Member, Long> {
    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Member create(@RequestBody @Valid Member one) {

        log.debug("one = {}", one);
        if (one.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post should NOT have ID!");
        }
        return memberService.saveOrReplace(one);
    }

    @GetMapping(value = {"/", "/all"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public List<Member> findAll() {
        return IterableUtils.toList(memberService.findAll());
    }

    // Single item
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Member findOne(@PathVariable(value = "id") Long id) {
        // Validate preconditions
        try {
            Validate.notNull(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Throwables.getRootCause(e).toString());
        }

        return memberService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "member NOT FOUND with Id:" + id));
    }

    @PutMapping(value = "/{id2Put}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public Member replace(@PathVariable("id2Put") Long id2Put, @RequestBody @Valid Member newMember) {
        // Validate preconditions
        try {
            Validate.notNull(id2Put);
            Validate.notNull(newMember);
            Validate.isTrue(!newMember.isEmpty());
            Validate.isTrue(memberService.existsById(id2Put));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Throwables.getRootCause(e).toString());
        }

        log.debug("id2Put = {}, newMember = {}", id2Put, newMember);
        return memberService.saveOrReplace(newMember);
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

        memberService.deleteById(id2Delete);
    }
}
