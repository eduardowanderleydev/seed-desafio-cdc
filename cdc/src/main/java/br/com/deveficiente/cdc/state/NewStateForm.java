package br.com.deveficiente.cdc.state;

import br.com.deveficiente.cdc.country.Country;
import br.com.deveficiente.cdc.shared.exceptions.ResourceNotFoundException;
import br.com.deveficiente.cdc.shared.validation.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

// 3 points of cognite load
public record NewStateForm(@UniqueValue(domainClass = State.class, fieldName = "name") String name,
                           @NotNull Long countryId) {
    public State toModel(EntityManager entityManager) {
        Country country = Optional.ofNullable(entityManager.find(Country.class, countryId)).orElseThrow(() -> new ResourceNotFoundException("country with id %s not found".formatted(countryId)));
        return new State(name, country);
    }
}
