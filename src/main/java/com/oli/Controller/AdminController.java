package com.oli.Controller;

import com.oli.Entities.Admin;
import com.oli.Services.AdminService;
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
@RequestMapping("/admins")
public class AdminController implements ControllerInterface<Admin, Long> {
    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Admin createOne(@Valid Admin one, BindingResult result) {
        ControllerUtils.validateResult(result);

        log.debug("one = {}", one);
        if (one.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post should NOT have ID!");
        }
        return service.saveOrReplace(one);
    }

    @GetMapping(value = {"/", "/all"})
    public List<Admin> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Admin> getOne(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PutMapping("/")
    public Admin replaceOne(@Valid Admin one, BindingResult result) {
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
