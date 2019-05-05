package com.oli.Controller;

import com.oli.EmailCfg;
import com.oli.Entities.Authority;
import com.oli.Entities.DropDownListEntities;
import com.oli.Entities.Member;
import com.oli.Entities.User;
import com.oli.Entities.Utils.EnumsUtils;
import com.oli.Services.DropDownListEntityService;
import com.oli.Services.MemberService;
import com.oli.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final UserService userService;

    public RegisterController(DropDownListEntityService service, MemberService memberService, EmailCfg emailCfg, UserService userService) {
        this.service = service;
        this.memberService = memberService;
        this.emailCfg = emailCfg;
        this.userService = userService;
    }

    /**
     * For registering, create user with enabled false, also a token for verification.
     * Plus, put the token link inside verification email.
     *
     * @param userName
     * @return
     */
    @PostMapping(value = "/register/GetConfirmationCode")
    public String getConfirmationCode(@Valid @Email @RequestParam("username") String userName, @Valid @RequestParam("password") String password) {
        // filter abnormal inputs
        Validate.notBlank(userName);
        Validate.notBlank(password);
        log.debug("userName = {}; password = {}", userName, password);

        // Create a user with enabled false
        if (userService.existsByEmail(userName)) {
            return REDIRECT_PREFIX + "/login?error=Ouch! The email is already taken?! Try login?";
        }
        final UUID token = UUID.randomUUID();
        User user = new User(userName, passwordEncoder().encode(password), token);
        Member member = new Member(user, userName);
        memberService.saveOrReplace(member);

        // Create a mail sender
        log.debug("emailCfg = {}", emailCfg);
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
        mailMessage.setSubject("Welcome! Please simply confirm your email!" + this.emailCfg.getFrom());
        final String prefix = "http://localhost:8080/register/verify/";
        mailMessage.setText("Welcome! Please click the link to confirm your email!\r\n" + prefix + userName + '/' + token);
        log.debug("mailMessage = {}", mailMessage);
        // Send mail
        mailSender.send(mailMessage);
        return REDIRECT_PREFIX + "/signup?info=Welcome! Please check email and confirm!";
    }

    @GetMapping(value = "/register/verify/{email}/{token}")
    public String verifyNewUserEmail(@Valid @Email @PathVariable("email") String email, @Valid @PathVariable("token") String token) {
        // filter abnormal inputs
        Validate.notBlank(email);
        Validate.notBlank(token);
        log.debug("email = {}; token = {}", email, token);
        UUID uuid = UUID.fromString(token.trim());
        Validate.notNull(uuid);

        // find user by email
        Optional<User> userOptional = userService.findByEmail(email);
        if (!userOptional.isPresent()) {
            return REDIRECT_PREFIX + "/login?error=Ouch! No such user with such email address!";
        }
        User user = userOptional.get();
        log.debug("user = {}", user);
        if (user.getEnabled()) {
            return REDIRECT_PREFIX + "/login?info=Welcome! Please log in.";
        } else if (user.getToken().equals(uuid)) {
            user.setEnabled(true);
            userService.saveOrReplace(user);
            return REDIRECT_PREFIX + "/login?info=Welcome! Please log in.";
        } else {
            return REDIRECT_PREFIX + "/login?error=Ouch! Wrong link, please make sure you got the correct verification link, no more, no less.";
        }
    }

    /**
     * simply get the page - the register form
     *
     * @param model
     * @return
     */
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

    /**
     * Post back the register form
     *
     * @param one
     * @param result
     * @return
     */
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
        HashSet<Authority> authorities = new HashSet<>(Collections.singletonList(new Authority(EnumsUtils.Authorities.ROLE_USER)));
        for (Authority authority : authorities) {
            authority.setUser(one.getUser());
        }
        one.getUser().setAuthorities(authorities);

        log.debug("one = {}", one);
        memberService.saveOrReplace(one);
        return REDIRECT_PREFIX + "/login?info=Welcome! Please log in.";
    }
}
