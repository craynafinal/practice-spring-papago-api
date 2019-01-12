package com.chaosdepot.services.papagoapi.conotrollers;

import com.chaosdepot.services.papagoapi.domains.PapagoTranslationContainer;
import com.chaosdepot.services.papagoapi.tests.PapagoTest;
import com.rits.cloning.Cloner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Papago api controller.
 */
@RestController
public class PapagoController {
    private PapagoTest papagoTest;

    public PapagoController() {
        papagoTest = new PapagoTest();
    }

    /**
     * Get bad request response entity.
     *
     * @param message error message to contain.
     * @return response entity with bad request
     */
    private ResponseEntity getBadRequestResponseEntity(String message) {
        PapagoTranslationContainer error = new PapagoTranslationContainer();
        error.setErrorMessage(message);
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Health check endpoint.
     *
     * @return 200 response entity.
     */
    @RequestMapping(value = {"/healthCheck"}, method = RequestMethod.GET)
    public ResponseEntity healthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body(new PapagoTranslationContainer());
    }

    /**
     * Translate text with post rest api call.
     *
     * @param container translation configuration
     * @return response entity with result
     */
    @RequestMapping(value = {"/papago" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity translateText(@RequestBody PapagoTranslationContainer container) {
        String errorMessage = container.validateData();

        if (!errorMessage.isEmpty()) {
            return getBadRequestResponseEntity(errorMessage);
        }

        try {
            PapagoTranslationContainer clone = new Cloner().deepClone(container);
            clone.setTargetText(papagoTest.shouldTranslate(container));
            return ResponseEntity.ok(clone);
        } catch (Throwable t) {
            return ResponseEntity.status(500).body(t);
        }
    }
}
