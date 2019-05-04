package com.oli;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${oliver.queries.users-query}")
    private String USERS_QUERY;

    @Value("${oliver.queries.roles-query}")
    private String AUTHORITIES_QUERY;

//    /**
//     * PoC: In-memory Authentication
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//                .and()
//                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
//    }

    /**
     * PRD: JDBC Authentication
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_QUERY)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * debug fail Authentication.
     */
    public class CustomAuthenticationFail implements AuthenticationFailureHandler {
        private ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            log.debug("request = {}", request.toString());
            log.debug("getAuthType = {}", request.getAuthType());
            log.debug("getProtocol = {}", request.getProtocol());
            log.debug("getQueryString = {}", request.getQueryString());
            log.debug("getUserPrincipal = {}", request.getUserPrincipal());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Map<String, Object> data = new HashMap<>();
            data.put("timestamp", Calendar.getInstance().getTime());
            data.put("exception", exception.getMessage());
            log.debug("data = {}", data);

            response.getOutputStream().println(objectMapper.writeValueAsString(data));
        }
    }

    /**
     * The core of security
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // for HTTPS
        http.requiresChannel();
        // filters
        http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/admins/**").hasRole("ADMIN")
                    .antMatchers("/payment/cancel", "/payment/success").permitAll()
                    .antMatchers("/", "/h2", "/login**", "/signup**", "/signup", "/GetConfirmationCode", "/register**", "/register/members/", "/register/**", "/home", "/index", "/landing").permitAll()
                    .antMatchers("/webjars/**", "/static/**", "/public/**", "/index", "/Index.html").permitAll()
                    .antMatchers("/users/**/verify/**", "/checkout").permitAll()
                    .antMatchers("/auth/**", "/oauth2/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/home", true)
                    .failureUrl("/login?error=Invalid username and password.")
                    .failureHandler(new CustomAuthenticationFail())//debug fail Authentication.
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/perform_logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
                    .permitAll()
                    .and()
                .httpBasic();
        // disabling csrf
        http.csrf().disable();
    }
}
