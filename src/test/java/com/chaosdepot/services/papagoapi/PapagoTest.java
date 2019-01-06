package com.chaosdepot.services.papagoapi;

import com.chaosdepot.services.papagoapi.domains.PapagoTranslationContainer;
import com.chaosdepot.services.papagoapi.enums.Language;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PapagoTest {
    @Test
    public void shouldReturnErrorWithoutSourceLanguage() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setTargetLanguage(Language.ENGLISH);
        container.setSourceText("hello");

        assertThat(container.validateData()).as("Should return error message")
                .isEqualTo("You need to pass source and target languages.");
    }

    @Test
    public void shouldReturnErrorWithoutTargetLanguage() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setSourceLanguage(Language.ENGLISH);
        container.setSourceText("hello");

        assertThat(container.validateData()).as("Should return error message")
                .isEqualTo("You need to pass source and target languages.");
    }

    @Test
    public void shouldReturnErrorWithoutSourceText() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setTargetLanguage(Language.ENGLISH);
        container.setSourceLanguage(Language.KOREAN);

        assertThat(container.validateData()).as("Should return error message")
                .isEqualTo("You need to pass source text.");
    }

    @Test
    public void shouldReturnOkayWithSourceLanguageTargetLanguageAndSourceText() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setTargetLanguage(Language.ENGLISH);
        container.setSourceLanguage(Language.KOREAN);
        container.setSourceText("hello");

        assertThat(container.validateData()).as("Should return error message")
                .isEqualTo("");
    }
}
