package com.chaosdepot.services.papagoapi.tests;

import com.chaosdepot.services.papagoapi.enums.Language;
import com.chaosdepot.services.papagoapi.screens.PapagoPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Performs test against Papago ui.
 * This will not use Testng to execute tests as this is expected to keep running
 * while spring boot api service is up.
 */
public class PapagoTest {
    private WebDriver webDriver;

    /**
     * Initialize web driver.
     */
    public PapagoTest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.setHeadless(false);
        webDriver = new ChromeDriver(options);
    }

    /**
     * Translate the language in Papago ui and return result in string.
     * @param translate string to translate
     * @param sourceLanaguage source language
     * @param targetLanguage target language
     */
    public String shouldTranslate(String translate, Language sourceLanaguage, Language targetLanguage) {
        if (translate.length() > 5000) {
            return "Cannot translate more than 5000 characters.";
        }

        String url = getUrl(translate, sourceLanaguage, targetLanguage);
        if (!webDriver.getCurrentUrl().equals(url)) {
            webDriver.get(url);
        }

        PapagoPage papagoPage = new PapagoPage(webDriver);
        return papagoPage.getTargetText();
    }

    /**
     * Exit web driver.
     */
    public void exitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    /**
     * Get complete Papago url.
     *
     * @param sourceLanguage source language
     * @param targetLanguage target language
     * @return complete url
     */
    private static String getUrl(String translate, Language sourceLanguage, Language targetLanguage) {
        String url = "https://papago.naver.com/?sk=[SOURCE]&tk=[TARGET]&st=[TRANSLATE]";
        return url.replace("[SOURCE]", sourceLanguage.value())
                .replace("[TARGET]", targetLanguage.value())
                .replace("[TRANSLATE]", translate.replaceAll(" ", "%20"));
    }
}
