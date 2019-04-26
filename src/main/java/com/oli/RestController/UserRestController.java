package com.oli.RestController;

import com.oli.Entities.User;
import com.oli.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Author: Oliver
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @PostMapping("/")
    private User createOne(@RequestBody User user) {
        return service.saveOrReplace(user);
    }

    @GetMapping(value = {"/", "/all"})
    private List<User> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    private Optional<User> getOne(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PutMapping("/")
    private User replaceOne(@RequestBody User user) {
        return service.saveOrReplace(user);
    }

    @DeleteMapping("/{id}")
    private void deleteOne(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
