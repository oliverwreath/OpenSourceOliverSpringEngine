package com.oli.RestController;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: Oliver
 */
public interface RestControllerInterface<DTO, T, ID> {
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public T create(@RequestBody @Valid T one);

    @GetMapping(value = {"/", "/all"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public List<T> findAll();

    // Single item
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public T findOne(@PathVariable(value = "id") ID id);

    @PutMapping(value = "/{id2Put}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public T replace(@PathVariable("id2Put") ID id2Put, @RequestBody @Valid DTO newEntity);

    @DeleteMapping(value = "/{id2Delete}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id2Delete") ID id2Delete);
}
