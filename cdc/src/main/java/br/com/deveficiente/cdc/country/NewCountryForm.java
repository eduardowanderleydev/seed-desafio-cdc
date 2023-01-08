package br.com.deveficiente.cdc.country;

import br.com.deveficiente.cdc.shared.validation.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record NewCountryForm(@NotBlank @UniqueValue(domainClass = Country.class, fieldName = "name") String name) {
    public Country toModel() {
        return new Country(name);
    }
}
