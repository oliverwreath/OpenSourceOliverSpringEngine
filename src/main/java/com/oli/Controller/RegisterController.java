package com.oli.Controller;

import com.oli.EmailCfg;
import com.oli.Entities.Authority;
import com.oli.Entities.DropDownListEntities;
import com.oli.Entities.Member;
import com.oli.Entities.Utils.EnumsUtils;
import com.oli.Services.DropDownListEntityService;
import com.oli.Services.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Email;
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
    private final EmailCfg emailCfg;

    public RegisterController(DropDownListEntityService service, MemberService memberService, EmailCfg emailCfg) {
        this.service = service;
        this.memberService = memberService;
        this.emailCfg = emailCfg;
    }

    @PostMapping(value = "/GetConfirmationCode")
    public String getConfirmationCode(@Valid @Email @RequestParam("username") String userName) {
        // filter abnormal inputs
        Validate.notNull(userName);
        log.debug("userName = {}", userName);

        log.debug("emailCfg = {}", emailCfg);
        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        log.debug("mailSender = {}", mailSender);
        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(this.emailCfg.getFrom());
        mailMessage.setTo(userName);
        mailMessage.setSubject("New feedback from " + this.emailCfg.getFrom());
        mailMessage.setText("Text success!");
        log.debug("mailMessage = {}", mailMessage);
        // Send mail
        mailSender.send(mailMessage);
        return REDIRECT_PREFIX + "/signup?info=Please check email for confirmation code!";
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
