package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.shared.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class PurchaseController {

    @PersistenceContext
    private EntityManager entityManager;

    private final StateBelongsToCountryValidator stateBelongsToCountryValidator;
    private final ValidCouponValidator validCouponValidator;

    public PurchaseController(StateBelongsToCountryValidator stateBelongsToCountryValidator, ValidCouponValidator validCouponValidator) {
        this.stateBelongsToCountryValidator = stateBelongsToCountryValidator;
        this.validCouponValidator = validCouponValidator;
    }

    @InitBinder
    public void addValidators(WebDataBinder binder) {
        binder.addValidators(stateBelongsToCountryValidator, validCouponValidator);
    }

    @PostMapping("/purchase/new")
    @Transactional
    public ResponseEntity newPurchase(@RequestBody @Valid NewPurchaseForm form) {
        Purchase purchase = entityManager.merge(form.toModel(entityManager));
        URI location = URI.create("/purchase/%s/details".formatted(purchase.getId()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/purchase/{id}/details")
    public ResponseEntity purchaseDetails(@PathVariable("id") Long id) {
        Purchase purchase = Optional.ofNullable(entityManager.find(Purchase.class, id)).orElseThrow(() -> new ResourceNotFoundException("Purchase with id %s not found".formatted(id)));
        return ResponseEntity.ok(new PurchaseDetailView(purchase));
    }

}
