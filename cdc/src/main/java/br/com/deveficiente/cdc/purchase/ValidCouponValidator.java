package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.coupon.Coupon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidCouponValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(NewPurchaseForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        NewPurchaseForm form = (NewPurchaseForm) target;

        if (!form.hasCoupon()) return;

        Coupon coupon = entityManager.find(Coupon.class, form.getCoupon());

        if (coupon == null || !coupon.isValid()) {
            errors.rejectValue("coupon", null, "Invalid coupon");
        }
    }
}
