package br.com.deveficiente.cdc.state;

import br.com.deveficiente.cdc.country.Country;
import br.com.deveficiente.cdc.shared.validation.ExistsEntityById;
import br.com.deveficiente.cdc.shared.validation.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;

// 2 points of cognite load
public record NewStateForm(@UniqueValue(domainClass = State.class, fieldName = "name") String name,
                           @ExistsEntityById(domainClass = Country.class) @NotNull Long countryId) {
    public State toModel(EntityManager entityManager) {
        Country country = entityManager.find(Country.class, countryId);
        return new State(name, country);
    }
}
