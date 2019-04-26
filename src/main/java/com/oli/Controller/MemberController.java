package com.oli.Controller;

import com.oli.Entities.Member;
import com.oli.Services.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Author: Oliver
 */
@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController implements ControllerInterface<Member, Long> {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Member createOne(@Valid Member one, BindingResult result) {
        ControllerUtils.validateResult(result);

        log.debug("one = {}", one);
        if (one.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post should NOT have ID!");
        }
        return service.saveOrReplace(one);
    }

    @GetMapping(value = {"/", "/all"})
    public List<Member> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Member> getOne(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PutMapping("/")
    public Member replaceOne(@Valid Member one, BindingResult result) {
        ControllerUtils.validateResult(result);

        return service.saveOrReplace(one);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
