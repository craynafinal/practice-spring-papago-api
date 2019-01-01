package com.chaosdepot.services.papagoapi.tests;

import com.chaosdepot.services.papagoapi.domains.PapagoTranslationContainer;
import com.chaosdepot.services.papagoapi.screens.PapagoPage;
import com.chaosdepot.services.papagoapi.utilities.WebDriverKiller;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.PreDestroy;

/**
 * Performs test against Papago ui.
 * This will not use Testng to execute tests as this is expected to keep running
 * while spring boot api conotrollers is up.
 */
public class PapagoTest {
    private WebDriver webDriver;

    /**
     * Initialize web driver.
     */
    public PapagoTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = getWebDriver();
        WebDriverKiller.setWebDriver(webDriver);
    }

    /**
     * Get Webdriver.
     * This will return headless browser.
     *
     * @return webdriver
     */
    private WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.setHeadless(true);
        return new ChromeDriver(options);
    }

    /**
     * Translate the language in Papago ui and return result in string.
     *
     * @param container configuration of translation
     */
    public String shouldTranslate(PapagoTranslationContainer container) {
        String url = getUrl(container);
        if (!webDriver.getCurrentUrl().equals(url)) {
            webDriver.get(url);
        }

        PapagoPage papagoPage = new PapagoPage(webDriver);
        return papagoPage.getTargetText();
    }

    /**
     * Exit web driver.
     */
    @PreDestroy
    public void exitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    /**
     * Get complete Papago url.
     *
     * @param container translation configuration
     * @return complete url
     */
    private static String getUrl(PapagoTranslationContainer container) {
        String url = "https://papago.naver.com/?sk=[SOURCE]&tk=[TARGET]&st=[TRANSLATE]";
        return url.replace("[SOURCE]", container.getSourceLanguage().value())
                .replace("[TARGET]", container.getTargetLanguage().value())
                .replace("[TRANSLATE]", container.getSourceText().replaceAll(" ", "%20"));
    }
}
