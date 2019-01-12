package com.chaosdepot.services.papagoapi;

import com.chaosdepot.services.papagoapi.domains.PapagoTranslationContainer;
import com.chaosdepot.services.papagoapi.enums.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PapagoApiApplicationTests {

    @LocalServerPort
    private int port;

    /**
     * Return papago url.
     *
     * @return papago url
     */
    public String getPapagoURL() {
        return getBaseURL() + "/papago";
    }

    /**
     * Return health check url.
     *
     * @return health check url
     */
    public String getHealthCheckURL() {
        return getBaseURL() + "/healthCheck";
    }

    /**
     * Return base url.
     *
     * @return base url
     */
    public String getBaseURL() {
        return "http://localhost:" + port;
    }

    /**
     * Get template with correct user credential.
     *
     * @return test rest template
     */
    public TestRestTemplate getCorrectUserTemplate() {
        return new TestRestTemplate("user", "pass");
    }

    /**
     * Get template with incorrect user credential.
     *
     * @return test rest template
     */
    public TestRestTemplate getIncorrectUserTemplate() {
        return new TestRestTemplate("wrong_user", "wrong_pass");
    }

    @Test
    public void shouldReturnOkayForHealthCheck() {
        ResponseEntity<String> response = getCorrectUserTemplate().getForEntity(getHealthCheckURL(), String.class);
        assertThat(response.getStatusCode()).as("Status code should be 200").isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnOkayWithCorrectCredential() throws IllegalStateException {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setTargetLanguage(Language.ENGLISH);
        container.setSourceLanguage(Language.KOREAN);
        container.setSourceText("hello");

        ResponseEntity<String> response = getCorrectUserTemplate().postForEntity(getPapagoURL(), container, String.class);
        assertThat(response.getStatusCode()).as("Status code should be 200").isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnAuthErrorWithIncorrectCredential() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setTargetLanguage(Language.ENGLISH);
        container.setSourceLanguage(Language.KOREAN);
        container.setSourceText("hello");

        ResponseEntity<String> response = getIncorrectUserTemplate().postForEntity(getPapagoURL(), container, String.class);

        assertThat(response.getStatusCode()).as("Status code should be 401").isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void shouldReturnBadErrorWithIncorrectRequestWithoutTargetLanguage() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setSourceLanguage(Language.KOREAN);
        container.setSourceText("hello");

        ResponseEntity<String> response = getCorrectUserTemplate().postForEntity(getPapagoURL(), container, String.class);

        assertThat(response.getStatusCode()).as("Status code should be 400").isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadErrorWithIncorrectRequestWithoutSourceLanguage() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setTargetLanguage(Language.ENGLISH);
        container.setSourceText("hello");

        ResponseEntity<String> response = getCorrectUserTemplate().postForEntity(getPapagoURL(), container, String.class);

        assertThat(response.getStatusCode()).as("Status code should be 400").isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadErrorWithIncorrectRequestWithoutSourceText() {
        PapagoTranslationContainer container = new PapagoTranslationContainer();
        container.setTargetLanguage(Language.ENGLISH);
        container.setSourceLanguage(Language.KOREAN);

        ResponseEntity<String> response = getCorrectUserTemplate().postForEntity(getPapagoURL(), container, String.class);

        assertThat(response.getStatusCode()).as("Status code should be 400").isEqualTo(HttpStatus.BAD_REQUEST);
    }
}

