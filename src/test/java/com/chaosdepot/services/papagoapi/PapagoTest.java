package com.chaosdepot.services.papagoapi;

import com.chaosdepot.services.papagoapi.conotrollers.PapagoController;
import com.chaosdepot.services.papagoapi.domains.PapagoTranslationContainer;
import com.chaosdepot.services.papagoapi.enums.Language;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PapagoTest {
    @Test
    public void shouldReturnErrorWithoutSourceLanguage() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setTargetLanguage(Language.ENGLISH);
        container.setSourceText("hello");

        PapagoController controller = new PapagoController();
        ResponseEntity responseEntity = controller.translateText(container);
        assertThat(responseEntity.getStatusCode()).as("Should throw 400 error code")
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnErrorWithoutTargetLanguage() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setSourceLanguage(Language.ENGLISH);
        container.setSourceText("hello");

        PapagoController controller = new PapagoController();
        ResponseEntity responseEntity = controller.translateText(container);
        assertThat(responseEntity.getStatusCode()).as("Should throw 400 error code")
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnErrorWithoutSourceText() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setTargetLanguage(Language.ENGLISH);
        container.setSourceLanguage(Language.KOREAN);

        PapagoController controller = new PapagoController();
        ResponseEntity responseEntity = controller.translateText(container);
        assertThat(responseEntity.getStatusCode()).as("Should throw 400 error code")
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnOkayWithSourceLanguageTargetLanguageAndSourceText() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setTargetLanguage(Language.ENGLISH);
        container.setSourceLanguage(Language.KOREAN);
        container.setSourceText("hello");

        PapagoController controller = new PapagoController();
        ResponseEntity responseEntity = controller.translateText(container);
        assertThat(responseEntity.getStatusCode()).as("Should return 200 code")
                .isEqualTo(HttpStatus.OK);
    }
}
