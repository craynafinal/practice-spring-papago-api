package com.chaosdepot.services.papagoapi.utilities;

import org.openqa.selenium.WebDriver;

import javax.annotation.PreDestroy;

/**
 * Saves web driver and kill when spring boot is terminated.
 */
public class WebDriverKiller {
    private static WebDriver webDriver;

    /**
     * Set web driver.
     *
     * @param webDriver web driver
     */
    public static void setWebDriver(WebDriver webDriver) {
        WebDriverKiller.webDriver = webDriver;
    }

    /**
     * Kill web driver if initialized.
     */
    @PreDestroy
    public static void destroy() {
        if (WebDriverKiller.webDriver != null) {
            WebDriverKiller.webDriver.quit();
        }
    }
}
