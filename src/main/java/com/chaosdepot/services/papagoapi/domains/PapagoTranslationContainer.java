package com.chaosdepot.services.papagoapi.domains;

import com.chaosdepot.services.papagoapi.enums.Language;

/**
 * Works as a pojo object for response and request.
 */
public class PapagoTranslationContainer {
    private Language sourceLanguage;
    private Language targetLanguage;
    private String sourceText;
    private String targetText;

    public Language getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(Language sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public Language getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(Language targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }
}
