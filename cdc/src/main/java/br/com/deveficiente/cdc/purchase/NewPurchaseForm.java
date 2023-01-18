package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.country.Country;
import br.com.deveficiente.cdc.coupon.Coupon;
import br.com.deveficiente.cdc.order.NewOrderForm;
import br.com.deveficiente.cdc.order.Order;
import br.com.deveficiente.cdc.shared.validation.Document;
import br.com.deveficiente.cdc.shared.validation.ExistsEntityByField;
import br.com.deveficiente.cdc.state.State;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;

import java.util.function.Function;

import static java.util.Objects.nonNull;

public class NewPurchaseForm {

    private @NotBlank @Email String email;
    private @NotBlank String name;
    private @NotBlank String lastName;
    private @NotBlank @Document String document;
    private @NotBlank String address;
    private @NotBlank String addressComplement;
    private @NotBlank String city;
    private @NotBlank String cep;
    @ExistsEntityByField(domainClass = Country.class, field = "id")
    private @NotNull Long countryId;
    @ExistsEntityByField(domainClass = State.class, field = "id")
    private Long stateId;
    private @NotBlank String phone;
    private @Valid NewOrderForm orderForm;
    private @ExistsEntityByField(domainClass = Coupon.class, field = "code") String coupon;

    public NewPurchaseForm(@NotBlank @Email String email,
                           @NotBlank String name,
                           @NotBlank String lastName,
                           @NotBlank @Document String document,
                           @NotBlank String address,
                           @NotBlank String addressComplement,
                           @NotBlank String city,
                           @NotBlank String cep,
                           @NotNull Long countryId,
                           Long stateId,
                           @NotBlank String phone,
                           @Valid NewOrderForm orderForm, String coupon) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.addressComplement = addressComplement;
        this.city = city;
        this.cep = cep;
        this.countryId = countryId;
        this.stateId = stateId;
        this.phone = phone;
        this.orderForm = orderForm;
        this.coupon = coupon;
    }

    public Purchase toModel(EntityManager entityManager) {
        Country country = entityManager.find(Country.class, countryId);

        Function<Purchase, Order> createOrderFunction = orderForm.toModel(entityManager);

        Coupon purchaseCoupon = hasCoupon() ? entityManager.find(Coupon.class, this.coupon) : null;

        Purchase purchase = new Purchase(email, name, lastName, document, address, addressComplement, city, cep, country, phone, purchaseCoupon, createOrderFunction);

        if (nonNull(stateId)) {
            State state = entityManager.find(State.class, stateId);
            purchase.setState(state);
        }

        return purchase;
    }

    private boolean hasCoupon() {
        return StringUtils.hasText(coupon);
    }

    public boolean hasState() {
        return nonNull(stateId);
    }

    public String getName() {
        return name;
    }

    public Long getCountryId() {
        return countryId;
    }

    public Long getStateId() {
        return stateId;
    }
}
