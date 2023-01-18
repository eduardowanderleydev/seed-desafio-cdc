package br.com.deveficiente.cdc.purchase;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        entityManager.persist(form.toModel(entityManager));
        return ResponseEntity.ok().build();
    }

}
