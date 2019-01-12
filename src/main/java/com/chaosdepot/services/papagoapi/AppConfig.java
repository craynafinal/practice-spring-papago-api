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
        auth
            .inMemoryAuthentication()
            .withUser(System.getenv().get("USER"))
            .password(System.getenv().get("PASSWORD"))
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
