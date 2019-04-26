package com.oli.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: Oliver
 */
@Slf4j
@Controller
@RequestMapping(value = "/public")
public class PublicController {
    @GetMapping(value = "/index")
    public String getClassroom() {
        return "index";
    }
}
