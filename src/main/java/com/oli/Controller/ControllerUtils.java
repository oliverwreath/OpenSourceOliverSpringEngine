package com.oli.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

/**
 * Author: Oliver
 */
@Slf4j
public class ControllerUtils {
    public static final String REDIRECT_PREFIX = "redirect:";

    public static void validateResult(BindingResult result) {
        if (result.hasErrors()) {
            log.debug("result = {}", result);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Binding result has errors!");
        }
    }
}
