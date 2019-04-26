package com.oli;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Author: Oliver
 */
@Slf4j
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        // landing
        registry.addRedirectViewController("/","/public/index");
        registry.addRedirectViewController("/landing","/public/index");
        registry.addRedirectViewController("/index","/public/index");
        registry.addRedirectViewController("/home","/public/index");
        // Other pages
        registry.addRedirectViewController("/public/","/public/index.html");
        registry.addRedirectViewController("/public/index","/public/index.html");
        registry.addRedirectViewController("/public/talent","/public/talent.html");
        registry.addRedirectViewController("/public/resume-check","/public/resume-check.html");
        registry.addRedirectViewController("/public/joinus","/public/joinus.html");
        registry.addRedirectViewController("/public/aboutus","/public/aboutus.html");
        registry.addRedirectViewController("/public/resume-check","/public/resume-check.html");
        // login/Register
        registry.addViewController("/login").setViewName("LoginRegister/login");
//        registry.addViewController("/register").setViewName("LoginRegister/register");
        // Payments
        registry.addViewController("/checkout").setViewName("Payment/checkout");
        registry.addViewController("/paypal").setViewName("Payment/paypal");
        registry.addViewController("/payment/cancel").setViewName("Payment/cancel");
        registry.addViewController("/payment/success").setViewName("Payment/success");
        // new board - fragments
        registry.addViewController("/general").setViewName("fragments/general");
        // new board - single layout
        registry.addViewController("/using_fragments").setViewName("layouts/using_fragments");
        registry.addViewController("/default_layout").setViewName("layouts/default_layout");
        // /admin/index
        registry.addViewController("/admin/index").setViewName("admin/index");
        registry.addViewController("/admin/members").setViewName("admin/show_members");
        registry.addViewController("/admin/courses").setViewName("admin/show_courses");
        registry.addViewController("/admin/videos").setViewName("admin/show_videos");
        // for testing
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
    }
}
