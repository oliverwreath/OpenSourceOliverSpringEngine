package com.oli.Controller;

import com.oli.Entities.Authority;
import com.oli.Entities.DropDownListEntities;
import com.oli.Entities.Member;
import com.oli.Entities.Utils.EnumsUtils;
import com.oli.Services.DropDownListEntityService;
import com.oli.Services.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

import static com.oli.Controller.ControllerUtils.REDIRECT_PREFIX;
import static com.oli.SecurityConfig.passwordEncoder;

/**
 * Author: Oliver
 */
@Slf4j
@Controller
public class RegisterController {
    private final DropDownListEntityService service;
    private final MemberService memberService;

    public RegisterController(DropDownListEntityService service, MemberService memberService) {
        this.service = service;
        this.memberService = memberService;
    }

    @GetMapping(value = "/register")
    public String getClassroom(Model model) {
        model.addAttribute("memberEntity", new Member());
        Optional<DropDownListEntities> dropDownCounties = service.findById(EnumsUtils.DropDownListEntities.COUNTRY);
        List<String> counties = dropDownCounties.isPresent() ? dropDownCounties.get().getMyValues() : new LinkedList<>();
        model.addAttribute("countries", counties);
        Optional<DropDownListEntities> dropDownCatalogs = service.findById(EnumsUtils.DropDownListEntities.CATALOG);
        List<String> catalogs = dropDownCatalogs.isPresent() ? dropDownCatalogs.get().getMyValues() : new LinkedList<>();
        model.addAttribute("catalogs", catalogs);
        return "LoginRegister/register";
    }

    @PostMapping(value = "/register/members/")
    public String saveOne(@Valid Member one, BindingResult result) {
        if (result.hasErrors()) {
            log.debug("result = {}", result);
            return REDIRECT_PREFIX + "/register?error=result.hasErrors()";
        }

        // must have User
        if (one.getUser() == null || one.getUser().isEmpty()) {
            log.debug("one = {}", one);
            throw new ResponseStatusException(HttpStatus.PARTIAL_CONTENT, "user is not set!");
        }
        // set user enabled
        one.getUser().setEnabled(true);
        // encrypt user password
        one.getUser().setPassword(passwordEncoder().encode(one.getUser().getPassword()));
        // set a default ROLE of simple user
        Set<Authority> set = new HashSet<Authority>();
        set.add(new Authority(EnumsUtils.Authorities.ROLE_USER));
        one.getUser().setAuthorities(set);

        log.debug("one = {}", one);
        memberService.saveOrReplace(one);
        return REDIRECT_PREFIX + "/login?info=Welcome! Please log in.";
    }
}
