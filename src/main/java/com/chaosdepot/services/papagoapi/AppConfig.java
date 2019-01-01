package com.chaosdepot.services.papagoapi;

import com.chaosdepot.services.papagoapi.utilities.WebDriverKiller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * App config that contains beans.
 */
@Configuration
public class AppConfig {
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
