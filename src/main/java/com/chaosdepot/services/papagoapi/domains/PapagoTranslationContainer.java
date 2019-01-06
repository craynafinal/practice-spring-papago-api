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
    private String errorMessage;

    /**
     * Validate data.
     *
     * @return error message if there is a problem; otherwise empty string
     */
    public String validateData() {
        if (getSourceLanguage() == null || getTargetLanguage() == null) {
            return "You need to pass source and target languages.";
        }

        if (getSourceText() == null || getSourceText().isEmpty()) {
            return "You need to pass source text.";
        }

        if (getSourceText().length() > 5000) {
            return "Cannot translate more than 5000 characters.";
        }

        return "";
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

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
