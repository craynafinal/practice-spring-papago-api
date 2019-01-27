package com.chaosdepot.services.papagoapi;

import com.chaosdepot.services.papagoapi.utilities.WebDriverKiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * App configuration.
 */
@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {
    /**
     * Setup user info.
     *
     * @param auth auth manager
     * @throws Exception auth exception
     */
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String username = System.getenv().get("USER");
        String password = System.getenv().get("PASSWORD");

        username = username == null || username.isEmpty() ? "user" : username;
        password = password == null || password.isEmpty() ? "{noop}pass" : password;

        auth
            .inMemoryAuthentication()
            .withUser(username)
            .password(password)
            .roles("USER", "ADMIN");
    }

    /**
     * Setup http security.
     *
     * @param http http security
     * @throws Exception http security exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic()
            .and().authorizeRequests().antMatchers("/papago").authenticated()
            .and().authorizeRequests().antMatchers("/healthCheck").permitAll()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Get web driver kill class.
     *
     * @return web driver kill
     */
    @Bean
    public WebDriverKiller taskKill() {
        return new WebDriverKiller();
    }
}
