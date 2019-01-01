package com.chaosdepot.services.papagoapi.conotrollers;

import com.chaosdepot.services.papagoapi.domains.PapagoTranslationContainer;
import com.chaosdepot.services.papagoapi.tests.PapagoTest;
import com.rits.cloning.Cloner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Papago api controller.
 */
@Controller
public class PapagoController {
    private PapagoTest papagoTest;

    public PapagoController() {
        papagoTest = new PapagoTest();
    }

    @RequestMapping(value = {"/papago" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCustomers(@RequestBody PapagoTranslationContainer container) {
        if (container.getSourceLanguage() == null || container.getTargetLanguage() == null) {
            return ResponseEntity.badRequest().body("You need to pass source and target languages.");
        }

        try {
            PapagoTranslationContainer clone = new Cloner().deepClone(container);
            clone.setTargetText(papagoTest.shouldTranslate(container.getSourceText(), container.getSourceLanguage(), container.getTargetLanguage()));
            return ResponseEntity.ok(clone);
        } catch (Throwable t) {
            return ResponseEntity.status(500).body(t);
        }
    }
}
