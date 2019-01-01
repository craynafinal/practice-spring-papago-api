package com.chaosdepot.services.papagoapi.screens;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Papago translation page.
 */
public class PapagoPage {
    @FindBy(how = How.ID, using = "sourceEditArea")
    private WebElement sourceText;
    @FindBy(how = How.ID, using = "txtTarget")
    private WebElement targetText;
    @FindBy(how = How.ID, using = "btnTranslate")
    private WebElement submitButton;

    private WebDriverWait wait;
    private static final int MAX_WAIT = 10;
    public static final int MAX_CHAR = 5000;

    /**
     * Webdriver is used for wait and initializing page factory elements.
     *
     * @param webDriver web driver
     */
    public PapagoPage(WebDriver webDriver) {
        wait = new WebDriverWait(webDriver, MAX_WAIT);
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Enter source text to translate.
     *
     * @param sourceText source text
     */
    public void enterSourceText(String sourceText) {
        wait.until(ExpectedConditions.visibilityOf(this.sourceText));
        this.sourceText.sendKeys(sourceText);
    }

    /**
     * Get translated text.
     *
     * @return translated text
     */
    public String getTargetText() {
        wait.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return !targetText.getText().isEmpty();
            }
        });
        return this.targetText.getText();
    }

    /**
     * Click translate button.
     */
    public void clickTranslateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(this.submitButton));
        this.submitButton.click();
    }
}
