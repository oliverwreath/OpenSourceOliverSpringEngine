package com.oli.Controller;

import com.oli.Entities.User;
import com.oli.Services.UserService;
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
@RequestMapping("/users")
public class UserController implements ControllerInterface<User, Long> {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/")
    public User createOne(@Valid User one, BindingResult result) {
        ControllerUtils.validateResult(result);

        log.debug("one = {}", one);
        if (one.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post should NOT have ID!");
        }
        return service.saveOrReplace(one);
    }

    @GetMapping(value = {"/", "/all"})
    public List<User> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getOne(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PutMapping("/")
    public User replaceOne(@Valid User one, BindingResult result) {
        ControllerUtils.validateResult(result);

        log.debug("one = {}", one);
        if (one.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Put should have ID!");
        }
        return service.saveOrReplace(one);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
