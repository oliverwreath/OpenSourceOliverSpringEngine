package com.oli.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebAppConfiguration
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@SpringBootConfiguration
//@ContextConfiguration(classes = MvcConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllersRegisterTest {
    @Autowired
    private WebApplicationContext ctx;
    private static MockMvc mockMvc;

    @BeforeAll
    void setup() {
        if (mockMvc == null) {
            mockMvc = MockMvcBuilders
                    .webAppContextSetup(this.ctx)
                    .apply(springSecurity())
                    .build();
        }
    }

    @Test
    void givenUrl_register1_whenNoUser_thenPartialContentResponse() throws Exception {
        // fail register
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/register/members/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(
                        EntityUtils.toString(
                                new UrlEncodedFormEntity(
                                        Arrays.asList(
                                                new BasicNameValuePair("name", "nam"),
                                                new BasicNameValuePair("school", "sch")
                                        )
                                )
                        )
                );
        MvcResult mvcResult = mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isPartialContent())
                .andReturn();
        log.debug("result = {}", mvcResult.getResponse().getContentAsString());

        // Login baby! - isUnauthorized() - 401
        RequestBuilder builder2 = formLogin().user("user_userEmail@777").password("user_password_WRONG");
        MvcResult mvcResult2 = mockMvc.perform(builder2)
                .andDo(print())
                .andExpect(status().isUnauthorized())
//                .andExpect(cookie().exists("JSESSIONID"))
                .andReturn();
        log.debug("mvcResult2 = {}", mvcResult2.getResponse().getContentAsString());
    }

    @Test
    void givenUrl_register2_whenGotUser_then3XXRedirectResponse() throws Exception {
        // successfully register
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/register/members/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(
                        EntityUtils.toString(
                                new UrlEncodedFormEntity(
                                        Arrays.asList(
                                                new BasicNameValuePair("name", "nam"),
                                                new BasicNameValuePair("school", "sch"),
                                                new BasicNameValuePair("user.userEmail", "user_userEmail@777"),
                                                new BasicNameValuePair("user.password", "user_password_963")
                                        )
                                )
                        )
                );
        MvcResult mvcResult = mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
        log.debug("mvcResult = {}", mvcResult.getResponse().getContentAsString());

        // Login baby! - isFound - 302
        RequestBuilder builder2 = formLogin().user("user_userEmail@777").password("user_password_963");
        MvcResult mvcResult2 = mockMvc.perform(builder2)
                .andDo(print())
                .andExpect(status().isFound())
//                .andExpect(cookie().exists("JSESSIONID"))
                .andReturn();
        log.debug("mvcResult2 = {}", mvcResult2.getResponse().getContentAsString());
    }
}
