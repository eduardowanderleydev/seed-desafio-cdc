package br.com.deveficiente.cdc.purchase;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class PurchaseController {

    private final StateBelongsToCountryValidator stateBelongsToCountryValidator;

    public PurchaseController(StateBelongsToCountryValidator stateBelongsToCountryValidator) {
        this.stateBelongsToCountryValidator = stateBelongsToCountryValidator;
    }

    @InitBinder
    public void addValidators(WebDataBinder binder) {
        binder.addValidators(stateBelongsToCountryValidator);
    }

    @PostMapping("/purchase/new")
    public ResponseEntity newPurchase(@RequestBody @Valid NewPurchaseForm form) throws URISyntaxException {

        return ResponseEntity.ok(form);
    }

}
