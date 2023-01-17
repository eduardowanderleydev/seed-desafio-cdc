package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.country.Country;
import br.com.deveficiente.cdc.state.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StateBelongsToCountryValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchaseForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        NewPurchaseForm form = (NewPurchaseForm) target;

        if (form.hasState()) {
            Country country = entityManager.find(Country.class, form.getCountryId());
            State state = entityManager.find(State.class, form.getStateId());

            if (!state.belongsTo(country)) {
                errors.rejectValue("stateId", null, "State doesnt belong to country");
            }
        }
    }
}
