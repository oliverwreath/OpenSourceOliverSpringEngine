package com.oli.Util;

import com.oli.EmailCfg;
import com.oli.OliverSpringEngineApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Properties;

/**
 * Author: Oliver
 */
@Slf4j
@Rollback
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OliverSpringEngineApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SendMailTests {
    private final String SEND_TO = "skywalkerhunter@gmail.com";
//    private final EmailCfg emailCfg;
    @Autowired
    EmailCfg emailCfg;

//    public SendMailTests(EmailCfg emailCfg) {
//        this.emailCfg = emailCfg;
//    }

    @BeforeAll
    void initialize() {
        log.info("initialize >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Test
    @Disabled
    @WithMockUser(roles = "ADMIN")
    void givenApplicationStarted_thenSendMail_shouldCompletedSuccessfully() {
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
        mailMessage.setTo(SEND_TO);
        mailMessage.setSubject("Welcome! Please simply confirm your email!" + this.emailCfg.getFrom());
        final String prefix = "http://localhost:8080/register/verify/";
        mailMessage.setText("Welcome! Please click the link to confirm your email!\r\n" + prefix + SEND_TO + '/' + "TOKEN");
        log.debug("mailMessage = {}", mailMessage);
        // Send mail
        mailSender.send(mailMessage);
        return;
    }

    @AfterAll
    void cleanUp() {
        log.info("cleanUp >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
