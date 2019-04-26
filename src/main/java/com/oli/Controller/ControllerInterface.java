package com.oli.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Author: Oliver
 */
@Controller
public interface ControllerInterface<T, ID> {
    @PostMapping("/")
    T createOne(@Valid T one, BindingResult result);

    @GetMapping(value = {"/", "/all"})
    List<T> getAll();

    @GetMapping("/{id}")
    Optional<T> getOne(@PathVariable("id") ID id);

    @PutMapping("/")
    T replaceOne(@Valid T one, BindingResult result);

    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable("id") ID id);
}
