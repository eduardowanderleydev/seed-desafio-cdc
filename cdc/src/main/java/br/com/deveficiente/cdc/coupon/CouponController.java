package br.com.deveficiente.cdc.coupon;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/coupon/new")
    @Transactional
    public ResponseEntity newCoupon(@RequestBody @Valid NewCouponForm newCouponForm) {
        entityManager.persist(newCouponForm.toModel());
        return ResponseEntity.ok().build();
    }
}
