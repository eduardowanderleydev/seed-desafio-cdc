package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.country.Country;
import br.com.deveficiente.cdc.shared.validation.Document;
import br.com.deveficiente.cdc.shared.validation.ExistsEntityById;
import br.com.deveficiente.cdc.state.State;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.nonNull;

public record NewPurchaseForm(@NotBlank @Email String email,
                              @NotBlank String name,
                              @NotBlank String lastName,
                              @NotBlank @Document String document,
                              @NotBlank String address,
                              @NotBlank String addressComplement,
                              @NotBlank String city,
                              @NotBlank String cep,
                              @ExistsEntityById(domainClass = Country.class) @NotNull Long countryId,
                              @ExistsEntityById(domainClass = State.class) Long stateId,
                              @NotBlank String phone,
                              @NotNull @Positive BigDecimal amount,
                              @NotEmpty List<@Valid NewPurchaseItemForm> items) {

    public Purchase toModel(EntityManager entityManager) {
        Country country = entityManager.find(Country.class, countryId);
        Purchase purchase = new Purchase(email, name, lastName, document, address, addressComplement, city, cep, country, phone, amount);
        if (nonNull(stateId)) {
            State state = entityManager.find(State.class, stateId);
            purchase.setState(state);
        }
        return purchase;
    }

    public boolean hasState() {
        return nonNull(stateId);
    }
}
